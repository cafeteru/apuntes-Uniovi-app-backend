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
 * Test the assignments to city of a address
 */
class CityTest {
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
    fun limitCity() {
        var city = ""
        for (i in 0 until AddressLimits.CITY) {
            city += "1"
        }
        address.city = city
        assertEquals(city, address.city)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitCity() {
        try {
            var city = ""
            for (i in 0..AddressLimits.CITY) {
                city += "1"
            }
            address.city = city
            fail("city is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, AddressMessages.LIMIT_CITY)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullCity() {
        address.city = null
        assertEquals(null, address.city)
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyCity() {
        try {
            address.city = ""
            fail("City can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, AddressMessages.LIMIT_CITY)
        }
    }
}