package es.uniovi.apuntesuniovi.infrastructure.messages.service

import es.uniovi.apuntesuniovi.models.types.LanguageType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.NoSuchMessageException
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.stereotype.Component
import java.util.*

/**
 * Component to load i18n messages
 */
@Component
class LoadMessages @Autowired constructor(
    private val messageSource: MessageSource
) {
    init {
        setLanguage(LanguageType.ES.toString())
    }

    private lateinit var accessor: MessageSourceAccessor

    /**
     * Set the language
     */
    final fun setLanguage(language: String) {
        val locale = Locale(language)
        accessor = MessageSourceAccessor(messageSource, locale)
    }

    /**
     * Get the 18n message
     */
    operator fun get(code: String?): String {
        code?.let {
            return try {
                accessor.getMessage(it)
            } catch (e: NoSuchMessageException) {
                it
            }
        }
        return ""
    }
}