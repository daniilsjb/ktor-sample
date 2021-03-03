package com.daniilb

import com.daniilb.common.error.DataNotFoundException
import com.daniilb.common.error.toError
import com.daniilb.component.quote.QuoteBindings
import com.google.inject.AbstractModule
import com.google.inject.Guice
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.serialization.*
import io.ktor.server.netty.*

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.main() {
    install(ContentNegotiation) {
        json()
    }

    install(StatusPages) {
        exception<DataNotFoundException> { cause ->
            call.respond(HttpStatusCode.NotFound, cause.toError())
        }
    }

    Guice.createInjector(MainBindings(this), QuoteBindings())
}

class MainBindings(private val application: Application) : AbstractModule() {

    override fun configure() {
        bind(Application::class.java).toInstance(application)
    }
}
