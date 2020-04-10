package es.uniovi.apuntesuniovi.servicies.dtos

interface DtoFactory {
    fun getSubjects(): SubjectDtoAssembler
}