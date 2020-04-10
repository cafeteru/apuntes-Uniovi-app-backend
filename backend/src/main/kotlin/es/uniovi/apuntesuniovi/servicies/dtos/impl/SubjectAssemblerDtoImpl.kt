package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.servicies.dtos.SubjectDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@Service
class SubjectAssemblerDtoImpl : SubjectDtoAssembler {
    override fun entityToDto(entity: Subject): SubjectDto {
        return SubjectDto(
                id = entity.id,
                name = entity.name,
                course = entity.course)
    }

    override fun dtoToEntity(dto: SubjectDto): Subject {
        return Subject(
                id = dto.id,
                name = dto.name,
                course = dto.course)
    }

    override fun listToDto(entityList: List<Subject>): List<SubjectDto> {
        val dtoList: MutableList<SubjectDto> = ArrayList<SubjectDto>()
        entityList.forEach(Consumer { entity: Subject -> dtoList.add(entityToDto(entity)) })
        return dtoList
    }

    override fun listToEntities(dtoList: List<SubjectDto>): List<Subject> {
        val entityList: MutableList<Subject> = ArrayList<Subject>()
        dtoList.forEach(Consumer { dto: SubjectDto -> entityList.add(dtoToEntity(dto)) })
        return entityList
    }
}