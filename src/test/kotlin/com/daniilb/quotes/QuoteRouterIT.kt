package com.daniilb.quotes

import com.daniilb.common.error.ServiceError
import com.daniilb.component.quote.endpoint.v1.request.QuotePostRequest
import com.daniilb.component.quote.endpoint.v1.request.QuotePutRequest
import com.daniilb.component.quote.service.Quote
import com.daniilb.main
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class QuoteRouterIT {

    @Test
    fun `should index quotes`() = withTestApplication(Application::main) {
        val quotes = listOf(
            Quote(1L, "We can only see a short distance ahead, but we can see plenty there that needs to be done.", "Alan Turing"),
            Quote(2L, "Anyone who attempts to generate random numbers by deterministic means is, of course, living in a state of sin.", "John von Neumann"),
            Quote(3L, "After all, C++ isn't a perfect match for Java's design aims either.", "Bjarne Stroustrup"),
            Quote(4L, "When in doubt, use brute force.", "Ken Thompson"),
            Quote(5L, "Do what you think is interesting, do something that you think is fun and worthwhile, because otherwise you won't do it well anyway.", "Brian Kernighan"),
            Quote(6L, "It is often easier to ask for forgiveness than to ask for permission.", "Grace Hopper"),
            Quote(7L, "We know the truth, not only by the reason, but also by the heart.", "Blaise Pascal"),
        )
        val expected = Json.encodeToString(quotes)

        with(handleRequest(HttpMethod.Get, "/quotes")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(expected, response.content)
        }
    }

    @Test
    fun `should find quote by id`() = withTestApplication(Application::main) {
        val quote = Quote(1L, "We can only see a short distance ahead, but we can see plenty there that needs to be done.", "Alan Turing")
        val expected = Json.encodeToString(quote)

        with(handleRequest(HttpMethod.Get, "/quotes/1")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(expected, response.content)
        }
    }

    @Test
    fun `should return error when quote is not found`() = withTestApplication(Application::main) {
        val error = ServiceError(404, "Could not retrieve quote with id '999'")
        val expected = Json.encodeToString(error)

        with(handleRequest(HttpMethod.Get, "/quotes/999")) {
            assertEquals(HttpStatusCode.NotFound, response.status())
            assertEquals(expected, response.content)
        }
    }

    @Test
    fun `should create new quote`() = withTestApplication(Application::main) {
        val request = QuotePostRequest("A wizard is never late, nor is he early. He arrives precisely when he means to.", "Gandalf the Gray")
        val payload = Json.encodeToString(request)

        val quote = Quote(8L, "A wizard is never late, nor is he early. He arrives precisely when he means to.", "Gandalf the Gray")
        val expected = Json.encodeToString(quote)

        with(handleRequest(HttpMethod.Post, "/quotes") {
            addHeader("Content-Type", "application/json")
            setBody(payload)
        }) {
            assertEquals(HttpStatusCode.Created, response.status())
            assertEquals(expected, response.content)
        }
    }

    @Test
    fun `should update existing quote`() = withTestApplication(Application::main) {
        val request = QuotePutRequest("A wizard is never late, nor is he early. He arrives precisely when he means to.", "Gandalf the Gray")
        val payload = Json.encodeToString(request)

        val quote = Quote(1L, "A wizard is never late, nor is he early. He arrives precisely when he means to.", "Gandalf the Gray")
        val expected = Json.encodeToString(quote)

        with(handleRequest(HttpMethod.Put, "/quotes/1") {
            addHeader("Content-Type", "application/json")
            setBody(payload)
        }) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(expected, response.content)
        }

        with(handleRequest(HttpMethod.Get, "/quotes/1")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(expected, response.content)
        }
    }

    @Test
    fun `should delete quote`() = withTestApplication(Application::main) {
        val error = ServiceError(404, "Could not retrieve quote with id '1'")
        val expected = Json.encodeToString(error)

        with(handleRequest(HttpMethod.Delete, "/quotes/1")) {
            assertEquals(HttpStatusCode.NoContent, response.status())
            assertEquals("{}", response.content)
        }

        with(handleRequest(HttpMethod.Get, "/quotes/1")) {
            assertEquals(HttpStatusCode.NotFound, response.status())
            assertEquals(expected, response.content)
        }
    }
}