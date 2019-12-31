package ar.com.cdeamerica.tupi.network

import androidx.lifecycle.MutableLiveData
import com.cr.o.cdc.sandboxAndroid.network.StatusResult

/**
 * Created by Camilo on 04/05/19.
 */
sealed class NetworkResource<T> {
    val valid: MutableLiveData<Boolean> = MutableLiveData()

    fun data(): T? = when (this) {
        is Success -> this.data
        is Loading -> this.data
        else -> null
    }

    fun status(): StatusResult = when (this) {
        is Success -> StatusResult.SUCCESS
        is Error -> StatusResult.FAILURE
        is Offline -> StatusResult.OFFLINE
        is Loading -> StatusResult.LOADING
    }

    data class Success<T>(val data: T) : NetworkResource<T>() {

        init {
            valid.postValue(true)
        }
    }

    data class Error<T>(val msg: String?, val data: T?) : NetworkResource<T>() {

        init {
            valid.value = true
        }
    }

    class Offline<T> : NetworkResource<T>() {
        init {
            valid.value = true
        }
    }

    data class Loading<T>(val data: T? = null) : NetworkResource<T>()
}