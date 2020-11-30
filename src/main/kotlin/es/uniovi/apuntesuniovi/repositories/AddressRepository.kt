package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.Address
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Manage the Address table
 */
interface AddressRepository : JpaRepository<Address, Long>