package es.uniovi.apuntesuniovi.repositories

interface RepositoryFactory {
    fun getStudents(): StudentRepository
    fun getSubjects(): SubjectRepository
}