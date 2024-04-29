package com.o2o.data.exception

import java.net.UnknownHostException

class BadRequestException(message: String?): Exception(message)
class UnauthorizedException(message: String?): Exception(message)
class ServiceNotFoundException(message: String?): Exception(message)
class ServerErrorException(message: String?): Exception(message)
class TimeoutException(message: String?): Exception(message)
class ConnectivityException(message: String?): UnknownHostException(message)
class GenericException(message: String?): Exception(message)