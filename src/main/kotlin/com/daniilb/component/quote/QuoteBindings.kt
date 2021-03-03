package com.daniilb.component.quote

import com.daniilb.component.quote.endpoint.v1.QuoteRouter
import com.daniilb.component.quote.service.DefaultQuoteService
import com.daniilb.component.quote.service.InMemoryQuoteRepository
import com.daniilb.component.quote.service.QuoteRepository
import com.daniilb.component.quote.service.QuoteService
import com.google.inject.AbstractModule

class QuoteBindings : AbstractModule() {

    override fun configure() {
        bind(QuoteRouter::class.java).asEagerSingleton()
        bind(QuoteService::class.java).to(DefaultQuoteService::class.java)
        bind(QuoteRepository::class.java).to(InMemoryQuoteRepository::class.java)
    }
}