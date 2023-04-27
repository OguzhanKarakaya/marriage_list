package com.main.marriage_list.common

data class ErrorType(val errorEnum: ErrorEnum, val error: Throwable) {
    fun getErrorMessage() = error.localizedMessage ?: "Unknown Error"
}

enum class ErrorEnum {
    AUTHENTICATING,
    CHECKING,
    DELETING,
    FETCHING,
    LOADING,
    RECEIVING,
    SAVING,
    SENDING,
    SUBMITING,
    UPLOADING
}