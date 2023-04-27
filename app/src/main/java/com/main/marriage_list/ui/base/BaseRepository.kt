package com.main.marriage_list.ui.base

import androidx.annotation.NonNull
import io.grpc.internal.SharedResourceHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class BaseRepository {
    protected fun <T> responseFlowWrapper(remoteCall: suspend () -> Resource<T>): Flow<Resource<T>> =
        flow {
            emit(Resource.loading())
            emit(remoteCall.invoke())
        }
}

class Resource<T> constructor(val status: Status, val data: T?, val error: Throwable? = null) {
    companion object {
        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return Resource(status = Status.ERROR, data = null, error = throwable)
        }

        fun <T> loading(): Resource<T> = Resource(Status.LOADING, null)
    }
}

enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}
