package es.uniovi.apuntesuniovi.repositories

/**
 * Factory to manage the repository classes
 */
interface RepositoryFactory {
    /**
     * Returns the users repository
     */
    fun getUsers(): UserRepository

    /**
     * Returns the subjects repository
     */
    fun getSubjects(): SubjectRepository
}