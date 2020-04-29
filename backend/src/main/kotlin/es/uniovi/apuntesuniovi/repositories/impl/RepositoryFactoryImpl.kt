package es.uniovi.apuntesuniovi.repositories.impl

import es.uniovi.apuntesuniovi.repositories.PersonRepository
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.repositories.StudentRepository
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Patrón Factory aplicado a la capa de repositorios
 */
@Service
class RepositoryFactoryImpl @Autowired constructor(
        private val personRepository: PersonRepository,
        private val studentRepository: StudentRepository,
        private val subjectRepository: SubjectRepository
) : RepositoryFactory {
    /**
     * Devuelve el repositorio de las personas
     */
    override fun getPersons(): PersonRepository {
        return personRepository
    }

    /**
     * Devuelve el repositorio de los estudiantes
     */
    override fun getStudents(): StudentRepository {
        return studentRepository
    }

    /**
     * Devuelve el repositorio de las asignaturas
     */
    override fun getSubjects(): SubjectRepository {
        return subjectRepository
    }
}