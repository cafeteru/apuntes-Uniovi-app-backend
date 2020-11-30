package es.uniovi.apuntesuniovi.servicies

/**
 * Factory to manage the service classes
 */
interface ServiceFactory {
    /**
     * Returns the subjects service
     */
    fun getSubjects(): SubjectService

    /**
     * Returns the university centers service
     */
    fun getUniversityCenters(): UniversityCenterService

    /**
     * Returns the users service
     */
    fun getUsers(): UserService
}