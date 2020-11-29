package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DtoFactoryImpl @Autowired constructor(
        private val subjectDtoAssembler: SubjectDtoAssembler,
        private val teachSubjectDtoAssembler: TeachSubjectDtoAssembler,
        private val userDtoAssembler: UserDtoAssembler,
) : DtoFactory {
    override fun getSubjects(): SubjectDtoAssembler {
        return subjectDtoAssembler
    }

    override fun getTeachSubjects(): TeachSubjectDtoAssembler {
        return teachSubjectDtoAssembler
    }

    override fun getUsers(): UserDtoAssembler {
        return userDtoAssembler
    }
}