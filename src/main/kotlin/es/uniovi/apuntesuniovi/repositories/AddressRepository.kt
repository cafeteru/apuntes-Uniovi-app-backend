package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Address
import es.uniovi.apuntesuniovi.repositories.interfaces.PagingQueryDslRepository

/**
 * Manage the Address table
 */
interface AddressRepository : PagingQueryDslRepository<Address>