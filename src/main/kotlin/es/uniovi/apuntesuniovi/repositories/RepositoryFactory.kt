package es.uniovi.apuntesuniovi.repositories

interface RepositoryFactory {
    fun getUsers(): UserRepository
    fun getSubjects(): SubjectRepository
    fun getRoles(): RoleRepository
}