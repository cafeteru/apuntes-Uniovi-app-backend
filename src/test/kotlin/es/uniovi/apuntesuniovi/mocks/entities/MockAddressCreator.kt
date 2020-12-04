package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.entities.Address
import es.uniovi.apuntesuniovi.mocks.MockCreator

/**
 * Service to create mock data of the entity Address
 */
class MockAddressCreator : MockCreator<Address> {
    override fun create(): Address {
        val address = Address()
        address.id = 1
        address.street = "street"
        address.city = "city"
        address.country = "country"
        address.postalCode = "postalCode"
        return address
    }
}