@file:Suppress("UNCHECKED_CAST")

package com.cr.o.cdc.sharedtest

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.cr.o.cdc.networking.ErrorResponse
import com.cr.o.cdc.networking.NetworkResponse
import com.cr.o.cdc.networking.SuccessResponse
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

fun Context.sharedPreferences(): SharedPreferences =
    getSharedPreferences(packageName, Context.MODE_PRIVATE).also {
        it.edit().clear().apply()
    }

@Throws(InterruptedException::class)
fun <T> getValueLiveData(
    liveData: LiveData<T>,
    seconds: Long,
    executeFunWhenObserve: () -> Unit? = {}
): T? {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            if (o != null) {
                data = o
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
    }
    liveData.observeForever(observer)
    executeFunWhenObserve.invoke()
    latch.await(seconds, TimeUnit.SECONDS)

    return data
}

@Throws(InterruptedException::class)
fun <T> getValueOfList(
    liveData: LiveData<T>,
    seconds: Long,
    executeFunWhenObserve: () -> Unit? = {}
): T? {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            if ((o as? List<*>)?.isNullOrEmpty() == false) {
                data = o
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
    }
    liveData.observeForever(observer)
    executeFunWhenObserve.invoke()
    latch.await(seconds, TimeUnit.SECONDS)

    return data
}

fun getMessage(id: String) = "la view con el id $id no tenia el texto esperado"


@Throws(InterruptedException::class)
fun <T> getCountOfChangesLiveData(
    liveData: LiveData<T>,
    seconds: Long,
    executeFunWhenObserve: () -> Unit

): Int {
    var count = 0
    val latch = CountDownLatch(1)
    liveData.observeForever {
        count += 1
    }

    Thread.sleep(400)
    executeFunWhenObserve.invoke()
    latch.await(seconds, TimeUnit.SECONDS)
    return count

}

fun <T> KClass<*>.makeRandomInstance(
    parameters: List<Parameter> = listOf()
): T = try {
    when (this) {
        List::class -> listOf<Any>()
        Boolean::class -> true
        Int::class -> Random.nextInt()
        Long::class -> Random.nextLong()
        Double::class -> Random.nextDouble(100.0, 200.0)
        Float::class -> Random.nextFloat()
        Char::class -> makeRandomString()[0]
        String::class -> makeRandomString()
        else -> (this.constructors as ArrayList<KFunction<Any>>)[0].let { kFunction ->
            kFunction.call(
                *kFunction.parameters.map { kParameter ->
                    val key = parameters.find { parameter ->
                        parameter.name == kParameter.name && parameter.place.qualifiedName == kParameter.toString()
                            .split(
                                ":"
                            )[1].replace(" ", "")
                    }
                    return@map key?.value
                        ?: (kParameter.type.classifier as KClass<*>).makeRandomInstance(parameters)
                }.toTypedArray()
            )
        }
    } as T
} catch (e: Exception) {
    println(this)
    println(e)
    throw IllegalStateException("problem with class $this")
}

data class Parameter(
    val name: String,
    val place: KClass<*>,
    val value: Any?
)

private fun makeRandomString(): String {
    val charPool = ('a'..'z') + ('A'..'Z')
    return (1..10)
        .map { Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}

fun <T> getValue(liveData: LiveData<NetworkResponse<T>>): NetworkResponse<T>? {
    var response: NetworkResponse<T>? = null
    val latch = CountDownLatch(1)
    liveData.observeForever {
        when (it) {
            is SuccessResponse -> {
                response = it
                latch.countDown()
            }
            is ErrorResponse -> {
                latch.countDown()
                throw Exception("Error in request")
            }
        }
    }
    latch.await(20, TimeUnit.SECONDS)
    return response
}
