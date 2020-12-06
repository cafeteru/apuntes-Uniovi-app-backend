package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.ConfigurationECTS
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Manage the ConfigurationECTS table
 */
interface ConfigurationECTSRepository : JpaRepository<ConfigurationECTS, Long>