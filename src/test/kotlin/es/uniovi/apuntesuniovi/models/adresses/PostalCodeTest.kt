package es.uniovi.apuntesuniovi.models.adresses

import es.uniovi.apuntesuniovi.models.Address
import es.uniovi.apuntesuniovi.infrastructure.constants.database.AddressLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.AddressMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockAddressCreator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to postalCode of a address
 */
class PostalCodeTest {
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
    fun limitPostalCode() {
        var postalCode = ""
        for (i in 0 until AddressLimits.POSTAL_CODE) {
            postalCode += "1"
        }
        address.postalCode = postalCode
        assertEquals(postalCode, address.postalCode)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitPostalCode() {
        try {
            var postalCode = ""
            for (i in 0..AddressLimits.POSTAL_CODE) {
                postalCode += "1"
            }
            address.postalCode = postalCode
            fail("postalCode is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, AddressMessages.LIMIT_POSTAL_CODE)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullPostalCode() {
        address.postalCode = null
        assertEquals(null, address.postalCode)
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyPostalCode() {
        try {
            address.postalCode = ""
            fail("postalCode can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, AddressMessages.LIMIT_POSTAL_CODE)
        }
    }
}