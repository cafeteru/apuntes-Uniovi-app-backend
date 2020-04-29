package es.uniovi.apuntesuniovi.servicies.dtos

interface DtoFactory {
    fun getPersons(): PersonDtoAssembler
    fun getSubjects(): SubjectDtoAssembler
}