package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DtoFactoryImpl @Autowired constructor(
        private val userDtoAssembler: UserDtoAssembler,
        private val subjectDtoAssembler: SubjectDtoAssembler
) : DtoFactory {
    override fun getUsers(): UserDtoAssembler {
        return userDtoAssembler
    }

    override fun getSubjects(): SubjectDtoAssembler {
        return subjectDtoAssembler
    }
}