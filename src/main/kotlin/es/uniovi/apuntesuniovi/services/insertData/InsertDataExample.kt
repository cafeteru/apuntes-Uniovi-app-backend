package es.uniovi.apuntesuniovi.services.insertData

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.Address
import es.uniovi.apuntesuniovi.models.User
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


  /**
   * Create initial data test
   */
  @PostConstruct
  fun initData() {
    logService.info("initData() - start")
    val admin = User()
    admin.id = null
    admin.name = "adminName"
    admin.surname = "adminSurname"
    admin.active = true
    admin.birthDate = LocalDate.of(1990, 12, 22)
    admin.email = "admin@admin.com"
    admin.identificationType = IdentificationType.DNI
    admin.img = null
    admin.numberIdentification = "72479503V"
    admin.password = "admin"
    admin.phone = "623548956"
    admin.username = "admin"
    admin.role = RoleType.ADMIN
    admin.address = null
    admin.language = LanguageType.ES

    val address = Address()
    address.city = "city"
    address.country = "country"
    address.street = "street"
    address.postalCode = "postalCode"
    admin.address = address
    userService.create(admin)
    logService.info("initData() - end")
  }
}