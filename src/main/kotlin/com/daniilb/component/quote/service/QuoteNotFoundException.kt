package com.daniilb.component.quote.service

import com.daniilb.common.error.DataNotFoundException

class QuoteNotFoundException(id: Long) : DataNotFoundException("Could not retrieve quote with id '$id'")
