package es.uniovi.apuntesuniovi.servicies.insertData

import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UniversityCenterDto
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
        val academy = UniversityCenterDto(
                id = null,
                name = "Academy",
                address = null
        )
        serviceFactory.getUniversityCenters().create(academy);

        var subjectDto = SubjectDto(id = null, name = "English")
        subjectDto = serviceFactory.getSubjects().create(subjectDto)[0]
        val admin = UserDto(
                id = 0,
                name = "adminName",
                surname = "adminSurname",
                active = true,
                birthDate = LocalDate.of(1990, 12, 22),
                email = "admin@admin.com",
                identificationType = "dni",
                img = null,
                numberIdentification = "72479503V",
                password = "admin",
                phone = "623548956",
                username = "admin",
                role = RoleType.ADMIN.toString()
        )
        serviceFactory.getUsers().create(admin)
        var teacher = UserDto(
                id = null,
                name = "teacherName",
                surname = "teacherSurname",
                active = true,
                birthDate = LocalDate.of(1957, 1, 19),
                email = "teacher@teacher.com",
                identificationType = "dni",
                img = null,
                numberIdentification = "93432683J",
                username = "teacher",
                password = "teacher",
                phone = "623548956",
                role = RoleType.TEACHER.toString()
        )
        teacher = serviceFactory.getUsers().create(teacher)[0]
        serviceFactory.getSubjects().addTeacher(subjectDto.id!!, teacher.id!!, LocalDate.now())
        val student = UserDto(
                id = null,
                name = "studentName",
                surname = "studentSurname",
                active = true,
                birthDate = LocalDate.of(1990, 9, 9),
                email = "student@student.com",
                identificationType = "dni",
                img = null,
                numberIdentification = "66524869Z",
                username = "student",
                password = "student",
                phone = "623548956",
                role = RoleType.STUDENT.toString()
        )
        serviceFactory.getUsers().create(student)
        logService.info("initData() - end")
    }
}