package es.uniovi.apuntesuniovi.services.insertData

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.Address
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.services.CareerService
import es.uniovi.apuntesuniovi.services.CenterService
import es.uniovi.apuntesuniovi.services.SubjectService
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.CareerDto
import es.uniovi.apuntesuniovi.services.dtos.entities.CenterDto
import es.uniovi.apuntesuniovi.services.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.annotation.PostConstruct

/**
 * Service to create test data
 */
@Service
class InsertDataExample @Autowired constructor(
    private val userService: UserService,
    private val subjectService: SubjectService,
    private val centerService: CenterService,
    private val careerService: CareerService
) {
    private val logService = LogService(this.javaClass)
    private var admin = UserDto(
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
        address = null
    )
    private var teacher = UserDto(
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
        phone = "623568956",
        role = RoleType.TEACHER.toString(),
        address = null
    )
    private var student = UserDto(
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
        phone = "623549956",
        role = RoleType.STUDENT.toString(),
        address = null
    )

    /**
     * Create initial data test
     */
    @PostConstruct
    fun initData() {
        logService.info("initData() - start")
        createAddress()
        val academy = createCenter("Academy")
        createCareer(academy)
        createCenter("Center")
        val subjectDto = createSubject("English")
        createSubject("TFG")
        createUsers()
        subjectService.addTeacher(subjectDto.id!!, teacher.id!!, LocalDate.now())
        logService.info("initData() - end")
    }

    private fun createAddress(): Address {
        val address = Address()
        address.city = "city"
        address.country = "country"
        address.postalCode = "postalCode"
        address.street = "street"
        return address
    }

    private fun createCenter(name: String): CenterDto {
        val academy = CenterDto(
            id = null,
            name = name,
            address = null
        )
        return centerService.create(academy)[0]
    }

    private fun createCareer(centerDto: CenterDto): CareerDto {
        val careerDto = CareerDto(
            id = 1,
            name = "career",
            centerId = centerDto.id
        )
        return careerService.create(careerDto)[0]
    }

    private fun createSubject(name: String): SubjectDto {
        val subjectDto = SubjectDto(null, name)
        return subjectService.create(subjectDto)[0]
    }

    private fun createUsers() {
        admin = userService.create(admin)[0]
        teacher = userService.create(teacher)[0]
        student = userService.create(student)[0]
    }
}