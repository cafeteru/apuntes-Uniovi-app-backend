package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.dtos.assemblers.UnitSubjectAssembler
import es.uniovi.apuntesuniovi.dtos.entities.UnitSubjectDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.UnitSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.unitSubjects.CreateUnitSubject
import es.uniovi.apuntesuniovi.services.commands.unitSubjects.FindUnitSubjectBySubjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UnitSubjectService @Autowired constructor(
    subjectRepository: SubjectRepository,
    private val unitSubjectRepository: UnitSubjectRepository
) {
    private val logService = LogService(this.javaClass)
    private val unitSubjectAssembler = UnitSubjectAssembler(subjectRepository)

    fun create(unitSubjectDto: UnitSubjectDto): UnitSubjectDto {
        logService.info("create(unitSubjectDto: UnitSubjectDto) - start")
        val unitSubject = unitSubjectAssembler.dtoToEntity(unitSubjectDto)
        val result = CreateUnitSubject(unitSubjectRepository, unitSubject).execute()
        logService.info("create(unitSubjectDto: UnitSubjectDto) - end")
        return unitSubjectAssembler.entityToDto(result)
    }

    fun findBySubjectId(id: Long, pageable: Pageable): Page<UnitSubjectDto> {
        logService.info("findBySubjectId(id: Long, pageable: Pageable) - start")
        val result = FindUnitSubjectBySubjectId(unitSubjectRepository, id, pageable).execute()
        logService.info("findBySubjectId(id: Long, pageable: Pageable) - end")
        return result.map { x -> unitSubjectAssembler.entityToDto(x) }
    }
}