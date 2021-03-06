package com.cr.o.cdc.sandboxAndroid

import android.content.SharedPreferences
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.cr.o.cdc.sandboxAndroid.libraries.networking.NetworkResponse
import com.squareup.picasso.Picasso

inline fun SharedPreferences.editAndApply(
    values: SharedPreferences.Editor.() -> SharedPreferences.Editor
) {
    edit().values().apply()
}

fun <T> ApolloQueryCall<T>.execute(): LiveData<NetworkResponse<T>> {
    val result = MutableLiveData<NetworkResponse<T>>()
    enqueue(object : ApolloCall.Callback<T>() {
        override fun onFailure(e: ApolloException) {
            result.postValue(NetworkResponse.create(e))
        }

        override fun onResponse(response: Response<T>) {
            result.postValue(NetworkResponse.create(response))
        }
    })

    return result
}

fun ImageView.loadFromUrl(
    imageUrl: String?
) {
    if (imageUrl != null) {
        visibility = View.VISIBLE
        Picasso.get().load(imageUrl).into(this)
    } else {
        visibility = View.GONE
    }
}

fun EditText.getInput(): String = this.text.toString()

fun <X, Y> LiveData<X>.mapGetValueIfNotEquals(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(Transformations.distinctUntilChanged(this), body)
}

fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}

fun <X, Y> LiveData<X>.mapGetValueIfNotEqualsAndNotNull(body: (X) -> Y): LiveData<Y> =
    MediatorLiveData<Y>().apply {
        this.addSource(this@mapGetValueIfNotEqualsAndNotNull) {
            it?.let { setValueIfNotEquals(body.invoke(it)) }
        }
    }

fun <T> MutableLiveData<T>.setValueIfNotEquals(newValue: T?) {
    if (value != newValue) {
        value = newValue
    }
}
