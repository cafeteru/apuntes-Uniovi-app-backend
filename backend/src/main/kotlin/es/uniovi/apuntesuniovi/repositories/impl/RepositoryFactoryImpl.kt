package es.uniovi.apuntesuniovi.repositories.impl

import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.repositories.StudentRepository
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RepositoryFactoryImpl @Autowired constructor(
        private val studentRepository: StudentRepository,
        private val subjectRepository: SubjectRepository
) : RepositoryFactory {

    override fun getStudents(): StudentRepository {
        return studentRepository
    }

    override fun getSubjects(): SubjectRepository {
        return subjectRepository
    }
}