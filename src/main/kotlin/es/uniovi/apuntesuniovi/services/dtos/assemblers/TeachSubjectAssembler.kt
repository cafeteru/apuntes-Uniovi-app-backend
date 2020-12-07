package es.uniovi.apuntesuniovi.services.dtos.assemblers

import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.subjects.FindSubjectByIdService
import es.uniovi.apuntesuniovi.services.commands.users.FindUserByIdService
import es.uniovi.apuntesuniovi.services.dtos.entities.TeachSubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Define the entity and dto conversion methods of teachSubjects
 */
@Service
class TeachSubjectAssembler @Autowired constructor(
    private val subjectRepository: SubjectRepository,
    private val userRepository: UserRepository
) : AbstractAssembler<TeachSubject, TeachSubjectDto>() {
    override fun entityToDto(entity: TeachSubject?): TeachSubjectDto {
        logService.info("entityToDto(entity: ${entity}) - start")
        entity?.let {
            val result = TeachSubjectDto(
                id = it.id,
                isCoordinator = it.isCoordinator,
                subjectId = it.subject.id,
                teacherId = it.teacher.id
            )
            logService.info("entityToDto(entity: ${entity}) - end")
            return result
        }
        logService.error("entityToDto(entity: ${entity}) - error")
        throw IllegalArgumentException()
    }

    override fun dtoToEntity(dto: TeachSubjectDto?): TeachSubject {
        logService.info("dtoToEntity(dto: ${dto}) - start")
        dto?.let {
            val result = TeachSubject()
            result.id = it.id
            result.isCoordinator = it.isCoordinator
            it.subjectId?.let { id ->
                result.subject = FindSubjectByIdService(subjectRepository, id).execute()[0]
            }
            it.teacherId?.let { id ->
                result.teacher = FindUserByIdService(userRepository, id).execute()[0]
            }
            logService.info("dtoToEntity(dto: ${dto}) - end")
            return result
        }
        logService.error("dtoToEntity(dto: ${dto}) - error")
        throw IllegalArgumentException()
    }
}