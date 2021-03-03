package com.daniilb.component.quote.service

import kotlinx.serialization.Serializable

@Serializable
data class Quote(val id: Long? = null, val content: String, val author: String)

data class QuoteEntity(var id: Long? = null, var content: String, var author: String)

fun Quote.toEntity() = QuoteEntity(
    id = this.id,
    content = this.content,
    author = this.author
)

fun QuoteEntity.toQuote() = Quote(
    id = this.id,
    content = this.content,
    author = this.author
)
