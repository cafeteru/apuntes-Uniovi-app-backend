package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.dtos.entities.TeachSubjectDto
import java.time.LocalDate

/**
 * Service to manage subjects
 */
interface SubjectService {
    /**
     * Add a teacher into the subject
     *
     * @param subjectId The subject's id
     * @param teacherId The teacher's id
     * @param date The date it started
     */
    fun addTeacher(subjectId: Long, teacherId: Long, date: LocalDate): List<TeachSubjectDto>

    /**
     * Saves the subject
     *
     * @param subjectDto Subject to save
     */
    fun create(subjectDto: SubjectDto): List<SubjectDto>

    /**
     * Returns all subjects
     */
    fun findAll(): List<SubjectDto>
}