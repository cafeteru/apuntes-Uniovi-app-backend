package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto

/**
 * Service to manage roles
 */
interface RoleService {
    /**
     * Returns all roles
     */
    fun findAll(): List<RoleDto>

    /**
     * Saves the role
     *
     * @param roleDto Role to save
     */
    fun save(roleDto: RoleDto): List<RoleDto>
}