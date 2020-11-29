package es.uniovi.apuntesuniovi.repositories

/**
 * Factory to manage the repository classes
 */
interface RepositoryFactory {
    /**
     * Returns the subjects repository
     */
    fun getSubjects(): SubjectRepository

    /**
     * Returns the teachSubjects repository
     */
    fun getTeachSubjects(): TeachSubjectRepository

    /**
     * Returns the users repository
     */
    fun getUsers(): UserRepository
}