package es.uniovi.apuntesuniovi.infrastructure.messages

import es.uniovi.apuntesuniovi.models.types.LanguageType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.NoSuchMessageException
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.stereotype.Component
import java.util.*

@Component
class LoadMessages @Autowired constructor(
  private val messageSource: MessageSource
) {
  init {
    setLanguage(LanguageType.ES.toString())
  }

  private lateinit var accessor: MessageSourceAccessor

  final fun setLanguage(language: String) {
    val locale = Locale(language)
    accessor = MessageSourceAccessor(messageSource, locale)
  }

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