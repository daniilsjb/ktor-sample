package com.daniilb.common.error

import kotlinx.serialization.Serializable

@Serializable
data class ServiceError(val code: Int, val message: String)