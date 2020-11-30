package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UniversityCenterDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UniversityCenterDtoAssembler
import es.uniovi.apuntesuniovi.servicies.impl.centers.SaveCenterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service to manage university center
 */
@Service
class CenterService @Autowired constructor(
        private val centerRepository: CenterRepository,
        private val centerDtoAssembler: UniversityCenterDtoAssembler
) {
    private val logService = LogService(this.javaClass)

    /**
     * Saves the subject
     *
     * @param universityCenterDto UniversityCenter to save
     */
    fun create(universityCenterDto: UniversityCenterDto): List<UniversityCenterDto> {
        logService.info("create(universityCenterDto: UniversityCenterDto) - start")
        val result = SaveCenterService(centerRepository, centerDtoAssembler,
                universityCenterDto).execute()
        logService.info("create(universityCenterDto: UniversityCenterDto) - end")
        return result
    }
}