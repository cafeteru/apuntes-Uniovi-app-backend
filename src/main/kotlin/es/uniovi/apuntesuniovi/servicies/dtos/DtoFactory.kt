package es.uniovi.apuntesuniovi.servicies.dtos

import es.uniovi.apuntesuniovi.servicies.dtos.impl.SubjectDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.impl.TeachSubjectDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UniversityCenterDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler

/**
 * Factory to handle the converters of entities to dtos
 */
interface DtoFactory {
    /**
     * Returns the subject assembler
     */
    fun getSubjects(): SubjectDtoAssembler

    /**
     * Returns the teachSubject assembler
     */
    fun getTeachSubjects(): TeachSubjectDtoAssembler

    /**
     * Returns the university center assembler
     */
    fun getUniversityCenters(): UniversityCenterDtoAssembler

    /**
     * Returns the user assembler
     */
    fun getUsers(): UserDtoAssembler


}