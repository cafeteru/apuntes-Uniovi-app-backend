package es.uniovi.apuntesuniovi.servicies.dtos

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

interface SubjectDtoAssembler {
    fun entityToDto(entity: Subject): SubjectDto
    fun dtoToEntity(dto: SubjectDto): Subject
    fun listToDto(entityList: List<Subject>): List<SubjectDto>
    fun listToEntities(dtoList: List<SubjectDto>): List<Subject>
}