package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

interface SubjectService {
    fun findAll(): List<SubjectDto>
    fun save(subjectDto: SubjectDto): List<SubjectDto>
}