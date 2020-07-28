package es.uniovi.apuntesuniovi.servicies.impl

import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.RoleService
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto
import es.uniovi.apuntesuniovi.servicies.impl.roles.FindAllRolesService
import es.uniovi.apuntesuniovi.servicies.impl.roles.SaveRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl @Autowired constructor(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory
) : RoleService {
    private val logService = LogService(this.javaClass)

    override fun findAll(): List<RoleDto> {
        logService.info("findAll() - start")
        val result = FindAllRolesService(repositoryFactory, dtoFactory).execute()
        logService.info("findAll() - end")
        return result
    }

    override fun save(roleDto: RoleDto): List<RoleDto> {
        logService.info("save(roleDto:${roleDto}) - start")
        val result = SaveRoleService(repositoryFactory, dtoFactory, roleDto).execute()
        logService.info("save(roleDto:${roleDto}) - end")
        return result
    }
}