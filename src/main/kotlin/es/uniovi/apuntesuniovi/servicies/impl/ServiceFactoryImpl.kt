package es.uniovi.apuntesuniovi.servicies.impl

import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.SubjectService
import es.uniovi.apuntesuniovi.servicies.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiceFactoryImpl @Autowired constructor(
        private val userService: UserService,
        private val subjectService: SubjectService
) : ServiceFactory {
    override fun getUsers(): UserService {
        return userService
    }

    override fun getSubjects(): SubjectService {
        return subjectService
    }
}