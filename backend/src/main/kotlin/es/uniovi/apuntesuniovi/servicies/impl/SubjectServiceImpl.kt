package es.uniovi.apuntesuniovi.servicies.impl

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SubjectServiceImpl @Autowired constructor(
        private val repositoryFactory: RepositoryFactory
) : SubjectService {

    override fun findAll(): List<Subject> {
        return repositoryFactory.getSubjects().findAll()
    }

    override fun save(subject: Subject): List<Subject> {
        val result = repositoryFactory.getSubjects().save(subject)
        val list = ArrayList<Subject>()
        list.add(result)
        return list
    }
}