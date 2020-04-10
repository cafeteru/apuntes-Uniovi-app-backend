package es.uniovi.apuntesuniovi.servicies

interface ServiceFactory {
    fun getSubjects(): SubjectService
}