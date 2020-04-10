package es.uniovi.apuntesuniovi.servicies.impl

import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiceFactoryImpl @Autowired constructor(
        private val subjectService: SubjectService
) : ServiceFactory {
    override fun getSubjects(): SubjectService {
        return subjectService
    }
}