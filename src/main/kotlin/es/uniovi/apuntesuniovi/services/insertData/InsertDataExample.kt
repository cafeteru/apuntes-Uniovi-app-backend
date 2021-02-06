package es.uniovi.apuntesuniovi.services.insertData

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.types.LanguageType
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.services.UserService
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
    identificationType = "dni",
    img = null,
    numberIdentification = "72479503V",
    password = "admin",
    phone = "623548956",
    username = "admin",
    role = RoleType.ADMIN.toString(),
    address = null,
    language = LanguageType.ES.toString()
  )

  /**
   * Create initial data test
   */
  @PostConstruct
  fun initData() {
    logService.info("initData() - start")
    userService.create(admin)
    logService.info("initData() - end")
  }
}