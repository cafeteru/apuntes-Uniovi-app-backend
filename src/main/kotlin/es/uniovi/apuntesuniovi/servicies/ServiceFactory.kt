package es.uniovi.apuntesuniovi.servicies

interface ServiceFactory {
    fun getUsers(): UserService
    fun getRoles(): RoleService
    fun getSubjects(): SubjectService
}