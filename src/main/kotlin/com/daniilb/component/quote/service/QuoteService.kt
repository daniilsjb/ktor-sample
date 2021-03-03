package com.daniilb.component.quote.service

interface QuoteService {

    fun findAll(): List<Quote>

    fun find(id: Long): Quote?

    fun save(quote: Quote): Quote

    fun update(quote: Quote): Quote

    fun delete(id: Long)
}