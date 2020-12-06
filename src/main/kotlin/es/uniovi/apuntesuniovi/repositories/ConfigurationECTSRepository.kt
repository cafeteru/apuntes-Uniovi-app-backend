package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.ConfigurationECTS
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Manage the ConfigurationECTS table
 */
interface ConfigurationECTSRepository : JpaRepository<ConfigurationECTS, Long>