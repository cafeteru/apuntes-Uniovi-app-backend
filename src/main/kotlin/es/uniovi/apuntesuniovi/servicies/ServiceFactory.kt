package es.uniovi.apuntesuniovi.servicies

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Factory to manage the service classes
 */
@Service
class ServiceFactory @Autowired constructor(
        private val subjectService: SubjectService,
        private val universityCenterService: CenterService,
        private val userService: UserService
) {

    /**
     * Returns the subjects service
     */
    fun getSubjects(): SubjectService {
        return subjectService
    }

    /**
     * Returns the university centers service
     */
    fun getUniversityCenters(): CenterService {
        return universityCenterService
    }

    /**
     * Returns the users service
     */
    fun getUsers(): UserService {
        return userService
    }

}