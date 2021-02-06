package es.uniovi.apuntesuniovi.models.careers

import es.uniovi.apuntesuniovi.infrastructure.messages.CareerMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockCareerCreator
import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.models.types.LanguageType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

/**
 * Test the assignments to language of a career
 */
class LanguageTest {
  private lateinit var career: Career

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initData() {
    career = MockCareerCreator().create()
    career.languages = HashSet<LanguageType>()
  }

  /**
   * Checks the assignment with valid data
   */
  @Test
  fun validLanguage() {
    career.addLanguage(LanguageType.ES.toString())
    assertTrue(career.languages.contains(LanguageType.ES))
    career.addLanguage(LanguageType.ES.toString())
    assertEquals(career.languages.size, 1)
  }

  /**
   * Checks the assignment with invalid data
   */
  @Test
  fun invalidLanguage() {
    try {
      career.addLanguage("No exists")
      fail("Language is invalid")
    } catch (e: IllegalArgumentException) {
      assertTrue(career.languages.isEmpty())
      assertEquals(e.message, CareerMessages.INVALID_LANGUAGE)
    }
  }

  /**
   * Checks the assignment to null
   */
  @Test
  fun nullLanguage() {
    try {
      career.addLanguage(null)
      fail("Language is null")
    } catch (e: IllegalArgumentException) {
      assertTrue(career.languages.isEmpty())
      assertEquals(e.message, CareerMessages.INVALID_LANGUAGE)
    }
  }

  /**
   * Checks the assignment to empty
   */
  @Test
  fun emptyLanguage() {
    try {
      career.addLanguage("")
      fail("Language is empty")
    } catch (e: IllegalArgumentException) {
      assertTrue(career.languages.isEmpty())
      assertEquals(e.message, CareerMessages.INVALID_LANGUAGE)
    }
  }
}