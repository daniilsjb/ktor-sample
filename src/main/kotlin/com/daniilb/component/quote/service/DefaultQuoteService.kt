package com.daniilb.component.quote.service

import com.google.inject.Inject

class DefaultQuoteService @Inject constructor(
    private val repository: QuoteRepository
) : QuoteService {

    override fun findAll() = repository.findAll().map { it.toQuote() }

    override fun find(id: Long) = repository.find(id)?.toQuote()

    override fun save(quote: Quote) = repository.save(quote.toEntity()).toQuote()

    override fun update(quote: Quote) = repository.update(quote.toEntity()).toQuote()

    override fun delete(id: Long) = repository.delete(id)
}