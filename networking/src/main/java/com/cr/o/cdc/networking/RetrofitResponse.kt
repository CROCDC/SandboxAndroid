package com.cr.o.cdc.networking

import com.apollographql.apollo.api.Response as ApolloR
import retrofit2.Response as RetrofitR
import com.apollographql.apollo.exception.ApolloException


sealed class RetrofitResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ErrorResponse<T> = ErrorResponse(error)

        fun <T> create(error: ApolloException): ErrorResponse<T> = ErrorResponse(error)

        fun <T> create(response: RetrofitR<T>): RetrofitResponse<T> {
            val body = response.body()
            return if (body == null || response.code() == 204) {
                ErrorResponse(null)
            } else {
                SuccessResponse(body)
            }
        }

        fun <T> create(response: ApolloR<T>): RetrofitResponse<T> {
            val data = response.data()
            return if (data != null) {
                SuccessResponse(data)
            } else {
                ErrorResponse(data)
            }
        }
    }
}


data class SuccessResponse<T>(
    val data: T?
) : RetrofitResponse<T>()

data class ErrorResponse<T>(val error: Throwable?) : RetrofitResponse<T>()