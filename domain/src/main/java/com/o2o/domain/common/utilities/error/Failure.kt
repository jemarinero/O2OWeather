package com.o2o.domain.common.utilities.error

sealed class Failure {

    data object ServerError : Failure()
    data object TimeoutError : Failure()
    data object ConnectivityError : Failure()
    data object UnauthorizedError : Failure()
    data object NoDataError : Failure()
    data class ServiceError(val message: String?) : Failure()
    data class GenericError(val message: String?) : Failure()
}