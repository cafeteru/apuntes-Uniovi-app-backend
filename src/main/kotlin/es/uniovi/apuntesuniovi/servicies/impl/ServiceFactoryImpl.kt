package es.uniovi.apuntesuniovi.servicies.impl

import es.uniovi.apuntesuniovi.servicies.UserService
import es.uniovi.apuntesuniovi.servicies.RoleService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiceFactoryImpl @Autowired constructor(
        private val userService: UserService,
        private val roleService: RoleService,
        private val subjectService: SubjectService
) : ServiceFactory {
    override fun getUsers(): UserService {
        return userService
    }

    override fun getRoles(): RoleService {
        return roleService
    }

    override fun getSubjects(): SubjectService {
        return subjectService
    }
}