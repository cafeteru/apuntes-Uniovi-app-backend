package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.models.Center
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.services.commands.centers.CreateCenterService
import es.uniovi.apuntesuniovi.services.commands.centers.FindAllCentersService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.CenterAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.CenterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service

/**
 * Service to manage university center
 */
@Service
class CenterService @Autowired constructor(
    private val centerRepository: CenterRepository,
    centerAssembler: CenterAssembler
) : BaseService<Center, CenterDto>(centerRepository, centerAssembler) {

    override fun create(
        repository: PagingAndSortingRepository<Center, Long>,
        entity: Center
    ): List<Center> {
        return CreateCenterService(centerRepository, entity).execute()
    }

    override fun findAll(
        repository: PagingAndSortingRepository<Center, Long>,
        pageable: Pageable
    ): Page<Center> {
        return FindAllCentersService(centerRepository, pageable).execute()
    }
}