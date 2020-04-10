package es.uniovi.apuntesuniovi.servicies.messages.impl

import es.uniovi.apuntesuniovi.servicies.messages.LoadMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.NoSuchMessageException
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.stereotype.Component
import java.util.*


@Component
class LoadMessagesImpl @Autowired constructor(
        private val messageSource: MessageSource
) : LoadMessages {
    private lateinit var accessor: MessageSourceAccessor

    init {
        setLanguage("")
    }

    final override fun setLanguage(language: String) {
        var locale = Locale("")
        when (language) {
            "en" -> locale = Locale("en")
            "es" -> locale = Locale("es")
        }
        accessor = MessageSourceAccessor(messageSource, locale)
    }

    override fun getString(code: String): String {
        return try {
            accessor.getMessage(code)
        } catch (e: NoSuchMessageException) {
            e.message.toString()
        }
    }
}