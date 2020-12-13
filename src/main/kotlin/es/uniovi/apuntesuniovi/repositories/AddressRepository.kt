package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Address
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the Address table
 */
interface AddressRepository : PagingAndSortingRepository<Address, Long>