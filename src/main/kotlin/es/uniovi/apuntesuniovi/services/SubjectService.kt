package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.dtos.assemblers.SubjectAssembler
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.subjects.*
import es.uniovi.apuntesuniovi.statistics.SubjectStatistics
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

/**
 * Service to manage subjects
 */
@Service
class SubjectService @Autowired constructor(
    private val subjectRepository: SubjectRepository
) {
    private val logService = LogService(this.javaClass)
    private val subjectAssembler = SubjectAssembler()

    /**
     * Create a new subject
     *
     * @param dto Subject to create
     */
    fun create(dto: SubjectDto): SubjectDto {
        logService.info("create(dto: UserDto) - start")
        val subject = subjectAssembler.dtoToEntity(dto)
        val result = CreateSubject(subjectRepository, subject).execute()
        logService.info("create(dto: UserDto) - end")
        return subjectAssembler.entityToDto(result)
    }

    /**
     * Returns all subjects
     *
     * @param pageable Pageable
     */
    fun findAll(pageable: Pageable, subjectDto: SubjectDto?): Page<SubjectDto> {
        logService.info("findAll() - start")
        val result = FindAllSubjects(subjectRepository, subjectDto, pageable).execute()
        logService.info("findAll() - end")
        return result.map { entity -> subjectAssembler.entityToDto(entity) }
    }

    /**
     * Returns the subject whose id matches
     *
     * @param id Subject´s id
     */
    fun findById(id: Long): SubjectDto {
        logService.info("findById() - start")
        val subject = FindSubjectById(subjectRepository, id).execute()
        logService.info("findById() - end")
        return subjectAssembler.entityToDto(subject)
    }

    /**
     * Update a new subject
     *
     * @param id Subject´s id
     * @param dto Subject to update
     */
    fun update(id: Long, dto: SubjectDto): SubjectDto {
        logService.info("update(id: Long, dto: SubjectDto) - start")
        val subject = subjectAssembler.dtoToEntity(dto)
        val result = UpdateSubject(subjectRepository, id, subject).execute()
        logService.info("update(id: Long, dto: SubjectDto) - end")
        return subjectAssembler.entityToDto(result)
    }

    /**
     * Change the value active of a subject
     *
     * @param id Subject´s id
     * @param active New value to subject´s active
     */
    fun disable(id: Long, active: Boolean): SubjectDto {
        logService.info("disable(id: $id, active: $active) - start")
        val subject = DisableSubject(subjectRepository, id, active).execute()
        logService.info("disable(id: $id, active:  $active) - end")
        return subjectAssembler.entityToDto(subject)
    }

    /**
     * Delete a subject
     *
     * @param id Subject´s id
     */
    fun delete(id: Long): Boolean {
        logService.info("delete(id: $id) - start")
        val result = DeleteSubject(subjectRepository, id).execute()
        logService.info("delete(id: $id) - end")
        return result
    }

    /**
     * Return subject statistics
     */
    fun statistics(): SubjectStatistics {
        logService.info("statistics() - start")
        val result = GetSubjectStatistics(subjectRepository).execute()
        logService.info("statistics() - end")
        return result
    }
}