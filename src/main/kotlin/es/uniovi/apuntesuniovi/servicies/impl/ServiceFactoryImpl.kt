package es.uniovi.apuntesuniovi.servicies.impl

import es.uniovi.apuntesuniovi.servicies.PersonService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiceFactoryImpl @Autowired constructor(
        private val personService: PersonService,
        private val subjectService: SubjectService
) : ServiceFactory {
    override fun getPersons(): PersonService {
        return personService
    }

    override fun getSubjects(): SubjectService {
        return subjectService
    }
}