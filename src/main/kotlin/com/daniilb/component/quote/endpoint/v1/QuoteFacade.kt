package com.daniilb.component.quote.endpoint.v1

import com.daniilb.component.quote.endpoint.v1.request.QuotePostRequest
import com.daniilb.component.quote.endpoint.v1.request.QuotePutRequest
import com.daniilb.component.quote.service.Quote
import com.daniilb.component.quote.service.QuoteNotFoundException
import com.daniilb.component.quote.service.QuoteService
import com.google.inject.Inject

class QuoteFacade @Inject constructor(
    private val service: QuoteService
) {

    fun findAll() = service.findAll()

    fun find(id: Long) = service.find(id) ?: throw QuoteNotFoundException(id)

    fun save(request: QuotePostRequest) =
        request.toQuote().run {
            service.save(this)
        }

    fun update(id: Long, request: QuotePutRequest) =
        request.toQuote(id).run {
            service.update(this)
        }

    fun delete(id: Long) = service.delete(id)

    private fun QuotePostRequest.toQuote() = Quote(
        author = this.author,
        content = this.content
    )

    private fun QuotePutRequest.toQuote(id: Long) = Quote(
        id = id,
        author = this.author,
        content = this.content
    )
}
