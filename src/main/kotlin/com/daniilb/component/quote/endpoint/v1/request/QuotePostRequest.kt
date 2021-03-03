package com.daniilb.component.quote.endpoint.v1.request

import kotlinx.serialization.Serializable

@Serializable
data class QuotePostRequest(val content: String, val author: String)
