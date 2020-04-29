package es.uniovi.apuntesuniovi.servicies

interface ServiceFactory {
    fun getPersons(): PersonService
    fun getSubjects(): SubjectService
}