package es.uniovi.apuntesuniovi.servicies.dtos

import es.uniovi.apuntesuniovi.servicies.dtos.impl.SubjectDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler

/**
 * Factory to handle the converters of entities to dtos
 */
interface DtoFactory {
    /**
     * Returns the user assembler
     */
    fun getUsers(): UserDtoAssembler

    /**
     * Returns the subject assembler
     */
    fun getSubjects(): SubjectDtoAssembler
}