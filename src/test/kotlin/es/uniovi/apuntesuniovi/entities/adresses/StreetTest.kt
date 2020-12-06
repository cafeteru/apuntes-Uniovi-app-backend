package es.uniovi.apuntesuniovi.entities.adresses

import es.uniovi.apuntesuniovi.entities.Address
import es.uniovi.apuntesuniovi.infrastructure.constants.database.AddressLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.AddressMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockAddressCreator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to street of a address
 */
class StreetTest {
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
    fun limitStreet() {
        var street = ""
        for (i in 0 until AddressLimits.STREET) {
            street += "1"
        }
        address.street = street
        assertEquals(street, address.street)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitStreet() {
        try {
            var street = ""
            for (i in 0..AddressLimits.STREET) {
                street += "1"
            }
            address.street = street
            fail("Street is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, AddressMessages.LIMIT_STREET)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullStreet() {
        address.street = null
        assertEquals(null, address.street)
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyStreet() {
        try {
            address.street = ""
            fail("Street can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, AddressMessages.LIMIT_STREET)
        }
    }
}