package com.cr.o.cdc.networking

import com.apollographql.apollo.api.Response as ApolloR
import retrofit2.Response as RetrofitR
import com.apollographql.apollo.exception.ApolloException


sealed class NetworkResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ErrorResponse<T> = ErrorResponse(error)

        fun <T> create(error: ApolloException): ErrorResponse<T> = ErrorResponse(error)

        fun <T> create(response: RetrofitR<T>): NetworkResponse<T> {
            val body = response.body()
            return if (body == null || response.code() == 204) {
                ErrorResponse(null)
            } else {
                SuccessResponse(body)
            }
        }

        fun <T> create(response: ApolloR<T>): NetworkResponse<T> {
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
) : NetworkResponse<T>()

data class ErrorResponse<T>(val error: Throwable?) : NetworkResponse<T>()