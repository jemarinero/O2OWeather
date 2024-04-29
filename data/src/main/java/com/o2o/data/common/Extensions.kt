package com.o2o.data.common

import com.o2o.data.exception.BadRequestException
import com.o2o.data.exception.ServerErrorException
import com.o2o.data.exception.ServiceNotFoundException
import com.o2o.data.exception.TimeoutException
import com.o2o.data.exception.UnauthorizedException
import com.o2o.data.model.common.HttpCode
import retrofit2.Response

fun <T> Response<T>.mapToResult(): T? {
    return when(HttpCode.fromCode(code())) {
        HttpCode.OK -> body()
        HttpCode.BAD_REQUEST -> throw BadRequestException(errorBody()?.toString())
        HttpCode.UNAUTHORIZED,
        HttpCode.FORBIDDEN -> throw UnauthorizedException(errorBody()?.toString())
        HttpCode.NOT_FOUND -> throw ServiceNotFoundException(errorBody()?.toString())
        HttpCode.SERVER_ERROR ->  throw ServerErrorException(errorBody()?.toString())
        HttpCode.TIME_OUT -> throw TimeoutException(errorBody()?.toString())
    }
}
