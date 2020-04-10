package es.uniovi.apuntesuniovi.servicies.insertData

import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class InsertDataExample @Autowired constructor(
        private val serviceFactory: ServiceFactory
) {

    @PostConstruct
    fun initData() {
        val subjectDto = SubjectDto(id = null, name = "TFG", course = 4)
        serviceFactory.getSubjects().save(subjectDto)
    }
}