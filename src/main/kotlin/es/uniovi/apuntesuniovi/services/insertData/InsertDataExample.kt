package es.uniovi.apuntesuniovi.services.insertData

import es.uniovi.apuntesuniovi.dtos.entities.*
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.types.IdentificationType
import es.uniovi.apuntesuniovi.models.types.LanguageType
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.models.types.SubjectType
import es.uniovi.apuntesuniovi.services.*
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
    private val teachSubjectService: TeachSubjectService,
    private val learnSubjectService: LearnSubjectService,
    private val unitSubjectService: UnitSubjectService
) {
    private val logService = LogService(this.javaClass)

    /**
     * Create initial data test
     */
    @PostConstruct
    fun initData() {
        logService.info("initData() - start")
        val admin = UserDto(
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

        var teacher = UserDto(
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

        var student = UserDto(
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
        teacher = userService.create(teacher)
        student.address = address
        student = userService.create(student)

        var subjectDto = SubjectDto(
            name = "subject",
            subjectType = SubjectType.BASIC.toString(),
            id = null,
            active = true
        )
        subjectDto = subjectService.create(subjectDto)

        val teachSubjectDto = TeachSubjectDto(
            teacherId = teacher.id!!,
            id = null,
            subjectId = subjectDto.id!!
        )
        teachSubjectService.create(subjectDto.id!!, listOf(teachSubjectDto))

        val learnSubjectDto = LearnSubjectDto(
            id = null,
            subjectId = subjectDto.id!!,
            studentId = student.id!!
        )
        learnSubjectService.create(subjectDto.id!!, listOf(learnSubjectDto))

        val unitSubjectDto = UnitSubjectDto(
            id = null,
            name = "Unit 1",
            position = 1,
            subjectId = subjectDto.id!!
        )
        unitSubjectService.create(unitSubjectDto)
        logService.info("initData() - end")
    }
}