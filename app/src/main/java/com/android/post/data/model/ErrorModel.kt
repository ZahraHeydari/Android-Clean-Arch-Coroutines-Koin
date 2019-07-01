package com.android.post.data.model

/**
 * This class designed to show different types of errors through error status & message
 *
 * */
data class ErrorModel(
    val message: String?,
    val code: Int?,
    var errorStatus: ErrorStatus
) {
    constructor(errorStatus: ErrorStatus) : this(null, null, errorStatus)

    constructor(message: String?, errorStatus: ErrorStatus) : this(message, null, errorStatus)

    fun getErrorMessage(): String {
        return when (errorStatus) {
            ErrorStatus.NO_CONNECTION -> "No connection!"
            ErrorStatus.BAD_RESPONSE -> "Bad response!"
            ErrorStatus.TIMEOUT -> "Time out!"
            ErrorStatus.EMPTY_RESPONSE -> "Empty response!"
            ErrorStatus.NOT_DEFINED -> "Not defined!"
            ErrorStatus.UNAUTHORIZED -> "Unauthorized!"
        }
    }

    /**
     * various error status to know what happened if something goes wrong with a repository call
     */
    enum class ErrorStatus {
        /**
         * error in connecting to repository (Server or Database)
         */
        NO_CONNECTION,
        /**
         * error in getting value (Json Error, Server Error, etc)
         */
        BAD_RESPONSE,
        /**
         * Time out  error
         */
        TIMEOUT,
        /**
         * no data available in repository
         */
        EMPTY_RESPONSE,
        /**
         * an unexpected error
         */
        NOT_DEFINED,
        /**
         * bad credential
         */
        UNAUTHORIZED
    }
}