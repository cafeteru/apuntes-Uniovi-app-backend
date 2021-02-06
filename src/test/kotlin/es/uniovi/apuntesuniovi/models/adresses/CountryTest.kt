package es.uniovi.apuntesuniovi.models.adresses

import es.uniovi.apuntesuniovi.infrastructure.constants.database.AddressLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.AddressMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockAddressCreator
import es.uniovi.apuntesuniovi.models.Address
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to country of a address
 */
class CountryTest {
  private lateinit var address: Address

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initData() {
    address = MockAddressCreator().create()
  }

  /**
   * Checks the assignment under the limit
   */
  @Test
  fun limitCountry() {
    var country = ""
    for (i in 0 until AddressLimits.COUNTRY) {
      country += "1"
    }
    address.country = country
    assertEquals(country, address.country)
  }

  /**
   * Checks the assignment over the limit
   */
  @Test
  fun upLimitCountry() {
    try {
      var country = ""
      for (i in 0..AddressLimits.COUNTRY) {
        country += "1"
      }
      address.country = country
      fail("country is too big")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, AddressMessages.LIMIT_COUNTRY)
    }
  }

  /**
   * Checks the assignment to null
   */
  @Test
  fun nullCountry() {
    address.country = null
    assertEquals(null, address.country)
  }

  /**
   * Checks the assignment to empty
   */
  @Test
  fun emptyCountry() {
    try {
      address.country = ""
      fail("country can´t be empty")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, AddressMessages.LIMIT_COUNTRY)
    }
  }
}