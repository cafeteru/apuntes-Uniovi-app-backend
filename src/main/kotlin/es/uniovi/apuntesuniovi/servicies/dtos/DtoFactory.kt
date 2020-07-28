package es.uniovi.apuntesuniovi.servicies.dtos

interface DtoFactory {
    fun getUsers(): UserDtoAssembler
    fun getRoles(): RoleDtoAssembler
    fun getSubjects(): SubjectDtoAssembler
}