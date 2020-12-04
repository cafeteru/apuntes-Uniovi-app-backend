package es.uniovi.apuntesuniovi.servicies.insertData

import es.uniovi.apuntesuniovi.entities.Address
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.servicies.CenterService
import es.uniovi.apuntesuniovi.servicies.SubjectService
import es.uniovi.apuntesuniovi.servicies.UserService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.CenterDto
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.annotation.PostConstruct

@Service
class InsertDataExample @Autowired constructor(
    private val userService: UserService,
    private val subjectService: SubjectService,
    private val centerService: CenterService
) {
    private val logService = LogService(this.javaClass)

    @PostConstruct
    fun initData() {
        logService.info("initData() - start")
        val address = Address()
        address.city = "city"
        address.country = "country"
        address.postalCode = "postalCode"
        address.street = "street"

        val academy = CenterDto(
            id = null,
            name = "Academy",
            address = null
        )
        centerService.create(academy)

        val center = CenterDto(
            id = null,
            name = "center",
            address = null
        )
        centerService.create(center)

        var subjectDto = SubjectDto(id = null, name = "English")
        subjectDto = subjectService.create(subjectDto)[0]
        val admin = UserDto(
            id = null,
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
            role = RoleType.ADMIN.toString(),
            address = address
        )
        userService.create(admin)
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
            role = RoleType.TEACHER.toString(),
            address = address
        )
        teacher = userService.create(teacher)[0]
        subjectService.addTeacher(subjectDto.id!!, teacher.id!!, LocalDate.now())
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
            role = RoleType.STUDENT.toString(),
            address = address
        )
        userService.create(student)
        logService.info("initData() - end")
    }
}