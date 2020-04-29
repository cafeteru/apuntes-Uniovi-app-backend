package es.uniovi.apuntesuniovi.repositories

interface RepositoryFactory {
    fun getPersons(): PersonRepository
    fun getStudents(): StudentRepository
    fun getSubjects(): SubjectRepository
}