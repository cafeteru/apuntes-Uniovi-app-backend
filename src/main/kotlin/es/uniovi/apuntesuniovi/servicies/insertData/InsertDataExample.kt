package es.uniovi.apuntesuniovi.servicies.insertData

import es.uniovi.apuntesuniovi.entities.Person
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class InsertDataExample @Autowired constructor(
        private val serviceFactory: ServiceFactory,
        private val repositoryFactory: RepositoryFactory
) {
    private val logService = LogService(this.javaClass)

    @PostConstruct
    fun initData() {
        logService.info("initData() - start")
        val subjectDto = SubjectDto(id = null, name = "TFG", course = 4)
        serviceFactory.getSubjects().save(subjectDto)
        val admin = Person(id = null,
                name = "admin",
                surname = "admin",
                active = true,
                birthDate = "22-12-1990",
                email = "admin@admin.com",
                identificationType = "dni",
                img = "",
                numberIdentification = "",
                password = "admin",
                phone = "",
                role = "admin",
                username = "admin")
        repositoryFactory.getPersons().save(admin)
        logService.info("initData() - end")
    }
}