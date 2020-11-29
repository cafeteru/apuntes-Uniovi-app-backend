package es.uniovi.apuntesuniovi.repositories.impl

import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Implement the repository factory interface
 */
@Service
class RepositoryFactoryImpl @Autowired constructor(
        private val subjectRepository: SubjectRepository,
        private val teachSubjectRepository: TeachSubjectRepository,
        private val userRepository: UserRepository
) : RepositoryFactory {

    /**
     * Returns the repository of the subjects
     */
    override fun getSubjects(): SubjectRepository {
        return subjectRepository
    }

    override fun getTeachSubjects(): TeachSubjectRepository {
        return teachSubjectRepository
    }

    /**
     * Returns the repository of the users
     */
    override fun getUsers(): UserRepository {
        return userRepository
    }


}