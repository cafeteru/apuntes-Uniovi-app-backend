package es.uniovi.apuntesuniovi.repositories.impl

import es.uniovi.apuntesuniovi.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Implement the repository factory interface
 */
@Service
class RepositoryFactoryImpl @Autowired constructor(
        private val subjectRepository: SubjectRepository,
        private val teachSubjectRegistryRepository: TeachSubjectRegistryRepository,
        private val teachSubjectRepository: TeachSubjectRepository,
        private val universityCenterRepository: UniversityCenterRepository,
        private val userRepository: UserRepository
) : RepositoryFactory {

    override fun getSubjects(): SubjectRepository {
        return subjectRepository
    }

    override fun getTeachSubjectRegistries(): TeachSubjectRegistryRepository {
        return teachSubjectRegistryRepository
    }

    override fun getTeachSubjects(): TeachSubjectRepository {
        return teachSubjectRepository
    }

    override fun getUniversityCenters(): UniversityCenterRepository {
        return universityCenterRepository
    }

    override fun getUsers(): UserRepository {
        return userRepository
    }


}