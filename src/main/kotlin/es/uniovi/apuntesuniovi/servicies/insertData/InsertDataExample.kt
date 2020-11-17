package es.uniovi.apuntesuniovi.servicies.insertData

import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.annotation.PostConstruct

@Service
class InsertDataExample @Autowired constructor(
        private val serviceFactory: ServiceFactory
) {
    private val logService = LogService(this.javaClass)

    @PostConstruct
    fun initData() {
        logService.info("initData() - start")

        val subjectDto = SubjectDto(id = 0, name = "TFG")
        serviceFactory.getSubjects().create(subjectDto)
        val admin = UserDto(
                id = 0,
                name = "admin",
                surname = "admin",
                active = true,
                birthDate = LocalDate.of(1990, 12, 22),
                email = "admin@admin.com",
                identificationType = "dni",
                img = "",
                numberIdentification = "72479503V",
                password = "admin",
                phone = "623548956",
                username = "admin",
                role = RoleType.ADMIN.toString()
        )
        serviceFactory.getUsers().create(admin)
        logService.info("initData() - end")
    }
}