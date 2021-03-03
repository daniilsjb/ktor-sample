package com.daniilb.component.quote.endpoint.v1

import com.daniilb.component.quote.endpoint.v1.request.QuotePostRequest
import com.daniilb.component.quote.endpoint.v1.request.QuotePutRequest
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

class QuoteRouter @Inject constructor(
    private val application: Application,
    private val facade: QuoteFacade
) {

    init {
        application.routing()
    }

    private fun Application.routing() =
        routing {
            findAll()
            find()
            save()
            update()
            delete()
        }

    private fun Route.findAll() =
        get("/quotes") {
            call.respond(facade.findAll())
        }

    private fun Route.find() =
        get("/quotes/{id}") {
            val id = call.parameters["id"]!!.toLong()
            call.respond(facade.find(id))
        }

    private fun Route.save() =
        post("/quotes") {
            val request = call.receive<QuotePostRequest>()
            call.respond(HttpStatusCode.Created, facade.save(request))
        }

    private fun Route.update() =
        put("/quotes/{id}") {
            val id = call.parameters["id"]!!.toLong()
            val request = call.receive<QuotePutRequest>()
            call.respond(facade.update(id, request))
        }

    private fun Route.delete() =
        delete("/quotes/{id}") {
            val id = call.parameters["id"]!!.toLong()
            call.respond(HttpStatusCode.NoContent, facade.delete(id))
        }
}
