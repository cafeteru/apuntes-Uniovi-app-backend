package es.uniovi.apuntesuniovi.servicies.dtos

import es.uniovi.apuntesuniovi.servicies.dtos.impl.RoleDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.impl.SubjectDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler

/**
 * Factoria para manejar los convertidores de entidades a dtos
 */
interface DtoFactory {
    /**
     * Devuelve el convertidor de usuarios
     */
    fun getUsers(): UserDtoAssembler

    /**
     * Devuelve el convertidor de roles
     */
    fun getRoles(): RoleDtoAssembler

    /**
     * Devuelve el convertidor de asignaturas
     */
    fun getSubjects(): SubjectDtoAssembler
}