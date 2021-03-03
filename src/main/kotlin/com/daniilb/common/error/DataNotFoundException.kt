package com.daniilb.common.error

import io.ktor.http.*

open class DataNotFoundException(message: String) : Exception(message)

fun DataNotFoundException.toError() = ServiceError(
    code = HttpStatusCode.NotFound.value,
    message = this.message!!
)