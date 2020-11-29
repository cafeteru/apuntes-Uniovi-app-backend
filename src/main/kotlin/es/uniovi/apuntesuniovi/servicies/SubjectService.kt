package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto

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

    /**
     * Add a teacher into the subject
     *
     * @param subjectId The subject's id
     * @param teacherId The teacher's id
     */
    fun addTeacher(subjectId: Long, teacherId: Long): List<UserDto>
}