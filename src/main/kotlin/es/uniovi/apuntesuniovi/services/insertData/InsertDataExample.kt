package es.uniovi.apuntesuniovi.services.insertData

import es.uniovi.apuntesuniovi.dtos.entities.AddressDto
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.types.IdentificationType
import es.uniovi.apuntesuniovi.models.types.LanguageType
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.annotation.PostConstruct

/**
 * Service to create test data
 */
@Service
class InsertDataExample @Autowired constructor(
    private val userService: UserService
) {
    private val logService = LogService(this.javaClass)
    private var admin = UserDto(
        id = null,
        name = "adminName",
        surname = "adminSurname",
        active = true,
        birthDate = LocalDate.of(1990, 12, 22),
        email = "admin@admin.com",
        identificationType = IdentificationType.DNI.toString(),
        img = null,
        numberIdentification = "72479503V",
        password = "admin",
        phone = "623548956",
        username = "admin",
        role = RoleType.ROLE_ADMIN.toString(),
        address = null,
        language = LanguageType.ES.toString()
    )

    private var teacher = UserDto(
        id = null,
        name = "teacherName",
        surname = "teacherSurname",
        active = true,
        birthDate = LocalDate.of(1990, 12, 22),
        email = "teacher@teacher.com",
        identificationType = IdentificationType.DNI.toString(),
        img = null,
        numberIdentification = "72349675R",
        password = "teacher",
        phone = "623548256",
        username = "teacher",
        role = RoleType.ROLE_TEACHER.toString(),
        address = null,
        language = LanguageType.ES.toString()
    )

    private var student = UserDto(
        id = null,
        name = "studentName",
        surname = "studentSurname",
        active = true,
        birthDate = LocalDate.of(1990, 12, 22),
        email = "student@student.com",
        identificationType = IdentificationType.NIE.toString(),
        img = null,
        numberIdentification = "X3977063H",
        password = "student",
        phone = "623548256",
        username = "student",
        role = RoleType.ROLE_STUDENT.toString(),
        address = null,
        language = LanguageType.ES.toString()
    )

    /**
     * Create initial data test
     */
    @PostConstruct
    fun initData() {
        logService.info("initData() - start")
        val address = AddressDto(
            id = null,
            city = "city",
            country = "country",
            street = "street",
            postalCode = "postalCode"
        )
        admin.address = address
        userService.create(admin)
        teacher.address = address
        userService.create(teacher)
        student.address = address
        userService.create(student)
        logService.info("initData() - end")
    }
}