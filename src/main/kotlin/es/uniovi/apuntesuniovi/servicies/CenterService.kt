package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UniversityCenterDto
import es.uniovi.apuntesuniovi.servicies.impl.centers.SaveCenterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service to manage university center
 */
@Service
class CenterService @Autowired constructor(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory
) {
    private val logService = LogService(this.javaClass)

    /**
     * Saves the subject
     *
     * @param universityCenterDto UniversityCenter to save
     */
    fun create(universityCenterDto: UniversityCenterDto): List<UniversityCenterDto> {
        logService.info("create(universityCenterDto: UniversityCenterDto) - start")
        val result = SaveCenterService(repositoryFactory.getUniversityCenters(),
                dtoFactory.getUniversityCenters(), universityCenterDto).execute()
        logService.info("create(universityCenterDto: UniversityCenterDto) - end")
        return result
    }
}