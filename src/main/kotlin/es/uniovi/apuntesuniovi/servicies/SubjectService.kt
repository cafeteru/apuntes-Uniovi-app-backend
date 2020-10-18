package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

/**
 * Service to manage subjects
 */
interface SubjectService {
    /**
     * Returns all subjects
     */
    fun findAll(): List<SubjectDto>

    /**
     * Saves the subject
     *
     * @param subjectDto Subject to save
     */
    fun create(subjectDto: SubjectDto): List<SubjectDto>
}