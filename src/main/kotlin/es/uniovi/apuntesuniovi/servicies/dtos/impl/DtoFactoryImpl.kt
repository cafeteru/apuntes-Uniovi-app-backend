package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.PersonDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.SubjectDtoAssembler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DtoFactoryImpl @Autowired constructor(
        private val personDtoAssembler: PersonDtoAssembler,
        private val subjectDtoAssembler: SubjectDtoAssembler
) : DtoFactory {
    override fun getPersons(): PersonDtoAssembler {
        return personDtoAssembler
    }

    override fun getSubjects(): SubjectDtoAssembler {
        return subjectDtoAssembler
    }
}