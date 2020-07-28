package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.dtos.SubjectDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@Service
class SubjectAssemblerDtoImpl : SubjectDtoAssembler {
    private val logService = LogService(this.javaClass)

    override fun entityToDto(entity: Subject): SubjectDto {
        logService.info("entityToDto(entity: ${entity}) - start")
        val result = SubjectDto(
                id = entity.id,
                name = entity.name,
                course = entity.course)
        logService.info("entityToDto(entity: ${entity}) - end")
        return result
    }

    override fun dtoToEntity(dto: SubjectDto): Subject {
        logService.info("dtoToEntity(dto: ${dto}) - start")
        val result = Subject(
                id = dto.id,
                name = dto.name,
                course = dto.course)
        logService.info("dtoToEntity(dto: ${dto}) - end")
        return result
    }

    override fun listToDto(entityList: List<Subject>): List<SubjectDto> {
        logService.info("listToDto(entityList: ${entityList}) - start")
        val dtoList: MutableList<SubjectDto> = ArrayList()
        entityList.forEach(Consumer { entity: Subject -> dtoList.add(entityToDto(entity)) })
        logService.info("listToDto(entityList: ${entityList}) - end")
        return dtoList
    }

    override fun listToEntities(dtoList: List<SubjectDto>): List<Subject> {
        logService.info("listToEntities(dtoList: ${dtoList}) - start")
        val entityList: MutableList<Subject> = ArrayList()
        dtoList.forEach(Consumer { dto: SubjectDto -> entityList.add(dtoToEntity(dto)) })
        logService.info("listToEntities(dtoList: ${dtoList}) - end")
        return entityList
    }
}