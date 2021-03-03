package com.daniilb.component.quote.service

class InMemoryQuoteRepository : QuoteRepository {

    // Sample data for demonstration purposes
    private val quotes = mutableListOf(
        QuoteEntity(1L, "We can only see a short distance ahead, but we can see plenty there that needs to be done.", "Alan Turing"),
        QuoteEntity(2L, "Anyone who attempts to generate random numbers by deterministic means is, of course, living in a state of sin.", "John von Neumann"),
        QuoteEntity(3L, "After all, C++ isn't a perfect match for Java's design aims either.", "Bjarne Stroustrup"),
        QuoteEntity(4L, "When in doubt, use brute force.", "Ken Thompson"),
        QuoteEntity(5L, "Do what you think is interesting, do something that you think is fun and worthwhile, because otherwise you won't do it well anyway.", "Brian Kernighan"),
        QuoteEntity(6L, "It is often easier to ask for forgiveness than to ask for permission.", "Grace Hopper"),
        QuoteEntity(7L, "We know the truth, not only by the reason, but also by the heart.", "Blaise Pascal"),
    ).associateBy { it.id }.toMutableMap()

    private var counter = count()

    override fun index() = quotes.values.toList()

    override fun find(id: Long) = quotes[id]

    override fun save(quote: QuoteEntity) =
        quote.apply {
            id = ++counter
        }.also {
            quotes[it.id] = it
        }

    override fun update(quote: QuoteEntity) =
        quote.also {
            quotes[it.id] = it
        }

    override fun delete(id: Long) {
        quotes.remove(id)
    }

    override fun count() = quotes.size.toLong()
}