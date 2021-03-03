package com.daniilb.component.quote.service

interface QuoteRepository {

    fun index(): List<QuoteEntity>

    fun find(id: Long): QuoteEntity?

    fun save(quote: QuoteEntity): QuoteEntity

    fun update(quote: QuoteEntity): QuoteEntity

    fun delete(id: Long)

    fun count(): Long
}