package es.uniovi.apuntesuniovi.models.users

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.models.types.LanguageType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to language of a user
 */
class LanguageTest {
  private lateinit var user: User

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initData() {
    user = MockUserCreator().create()
  }

  /**
   * Checks the assignment with valid data
   */
  @Test
  fun validLanguage() {
    user.setLanguage(LanguageType.ES.toString())
    assertEquals(LanguageType.ES, user.language)
  }

  /**
   * Checks the assignment with invalid data
   */
  @Test
  fun invalidLanguage() {
    try {
      user.setLanguage("No exists")
      fail("Language is invalid")
    } catch (e: IllegalArgumentException) {
      assertEquals(LanguageType.ES, user.language)
      assertEquals(e.message, UserMessages.INVALID_LANGUAGE)
    }
  }

  /**
   * Checks the assignment to empty
   */
  @Test
  fun emptyLanguage() {
    try {
      user.setLanguage("")
      fail("Language can´t be empty")
    } catch (e: IllegalArgumentException) {
      assertEquals(LanguageType.ES, user.language)
      assertEquals(e.message, UserMessages.INVALID_LANGUAGE)
    }
  }

  /**
   * Checks the assignment to null
   */
  @Test
  fun nullLanguage() {
    val language = user.language
    user.setLanguage(null)
    assertNotNull(user.language)
    assertEquals(user.language, language)
  }
}