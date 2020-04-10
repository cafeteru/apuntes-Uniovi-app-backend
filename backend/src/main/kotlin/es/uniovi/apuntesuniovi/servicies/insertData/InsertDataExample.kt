package es.uniovi.apuntesuniovi.servicies.insertData

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class InsertDataExample @Autowired constructor(
        private val serviceFactory: ServiceFactory
) {

    @PostConstruct
    fun initData() {
        val subject = Subject()
        subject.name = "TFG";
        serviceFactory.getSubjects().save(subject)
    }
}