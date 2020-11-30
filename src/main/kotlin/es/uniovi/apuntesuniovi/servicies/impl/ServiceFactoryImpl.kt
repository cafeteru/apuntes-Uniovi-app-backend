package es.uniovi.apuntesuniovi.servicies.impl

import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.SubjectService
import es.uniovi.apuntesuniovi.servicies.UniversityCenterService
import es.uniovi.apuntesuniovi.servicies.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiceFactoryImpl @Autowired constructor(
        private val subjectService: SubjectService,
        private val universityCenterService: UniversityCenterService,
        private val userService: UserService
) : ServiceFactory {
    override fun getSubjects(): SubjectService {
        return subjectService
    }

    override fun getUniversityCenters(): UniversityCenterService {
        return universityCenterService
    }

    override fun getUsers(): UserService {
        return userService
    }


}