package es.uniovi.apuntesuniovi.servicies

/**
 * Factory to manage the service classes
 */
interface ServiceFactory {
    /**
     * Returns the users service
     */
    fun getUsers(): UserService

    /**
     * Returns the roles service
     */
    fun getRoles(): RoleService

    /**
     * Returns the subject service
     */
    fun getSubjects(): SubjectService
}