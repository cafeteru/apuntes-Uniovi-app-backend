package es.uniovi.apuntesuniovi.servicies.dtos

import es.uniovi.apuntesuniovi.servicies.dtos.impl.SubjectDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.impl.TeachSubjectDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UniversityCenterDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Factory to handle the converters of entities to dtos
 */
@Service
class DtoFactoryImpl @Autowired constructor(
        private val subjectDtoAssembler: SubjectDtoAssembler,
        private val teachSubjectDtoAssembler: TeachSubjectDtoAssembler,
        private val universityCenterDtoAssembler: UniversityCenterDtoAssembler,
        private val userDtoAssembler: UserDtoAssembler,
) {
    /**
     * Returns the subject assembler
     */
    fun getSubjects(): SubjectDtoAssembler {
        return subjectDtoAssembler
    }

    /**
     * Returns the teachSubject assembler
     */
    fun getTeachSubjects(): TeachSubjectDtoAssembler {
        return teachSubjectDtoAssembler
    }

    /**
     * Returns the university center assembler
     */
    fun getUniversityCenters(): UniversityCenterDtoAssembler {
        return universityCenterDtoAssembler
    }

    /**
     * Returns the user assembler
     */
    fun getUsers(): UserDtoAssembler {
        return userDtoAssembler
    }
}