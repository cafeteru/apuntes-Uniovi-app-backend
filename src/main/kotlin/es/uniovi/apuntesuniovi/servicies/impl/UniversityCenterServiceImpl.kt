package es.uniovi.apuntesuniovi.servicies.impl

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.UniversityCenterService
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UniversityCenterDto
import es.uniovi.apuntesuniovi.servicies.impl.universityCenters.SaveUniversityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UniversityCenterServiceImpl @Autowired constructor(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory
) : UniversityCenterService {
    private val logService = LogService(this.javaClass)

    override fun create(universityCenterDto: UniversityCenterDto): List<UniversityCenterDto> {
        logService.info("create(universityCenterDto: UniversityCenterDto) - start")
        val result = SaveUniversityService(repositoryFactory.getUniversityCenters(),
                dtoFactory.getUniversityCenters(), universityCenterDto).execute()
        logService.info("create(universityCenterDto: UniversityCenterDto) - end")
        return result
    }
}