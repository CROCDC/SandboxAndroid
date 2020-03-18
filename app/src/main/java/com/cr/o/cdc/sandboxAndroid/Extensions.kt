package com.cr.o.cdc.sandboxAndroid

import android.content.SharedPreferences
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.cr.o.cdc.networking.RetrofitResponse
import com.squareup.picasso.Picasso

inline fun SharedPreferences.editAndApply(values: SharedPreferences.Editor.() -> SharedPreferences.Editor) {
    edit().values().apply()
}

fun <T> ApolloQueryCall<T>.execute(): LiveData<RetrofitResponse<T>> {
    val result = MutableLiveData<RetrofitResponse<T>>()
    enqueue(object : ApolloCall.Callback<T>() {
        override fun onFailure(e: ApolloException) {
            result.postValue(RetrofitResponse.create(e))
        }

        override fun onResponse(response: Response<T>) {
            result.postValue(RetrofitResponse.create(response))
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
