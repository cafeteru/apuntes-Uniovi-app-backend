package es.uniovi.apuntesuniovi.repositories

import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.mocks.dtos.MockUserDtoCreator
import es.uniovi.apuntesuniovi.models.QUser
import es.uniovi.apuntesuniovi.models.types.IdentificationType
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.builders.UserBuilder
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test class UserBuilder
 */
class UserBuilderTest {
  private lateinit var userDto: UserDto
  private lateinit var qUser: QUser

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initData() {
    userDto = MockUserDtoCreator().create()
    qUser = QUser.user
  }

  @Test
  fun nullName() {
    val expression = qUser.name.like(
      Expressions.asString("%").concat(userDto.name).concat("%")
    )
    userDto.name = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun emptyName() {
    val expression = qUser.name.like(
      Expressions.asString("%").concat(userDto.name).concat("%")
    )
    userDto.name = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun nullSurname() {
    val expression = qUser.surname.like(
      Expressions.asString("%").concat(userDto.surname).concat("%")
    )
    userDto.surname = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun emptySurname() {
    val expression = qUser.surname.like(
      Expressions.asString("%").concat(userDto.surname).concat("%")
    )
    userDto.surname = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun nullEmail() {
    val expression = qUser.email.like(
      Expressions.asString("%").concat(userDto.email).concat("%")
    )
    userDto.email = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun emptyEmail() {
    val expression = qUser.email.like(
      Expressions.asString("%").concat(userDto.email).concat("%")
    )
    userDto.email = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun nullPhone() {
    val expression = qUser.phone.like(
      Expressions.asString("%").concat(userDto.phone).concat("%")
    )
    userDto.phone = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun emptyPhone() {
    val expression = qUser.phone.like(
      Expressions.asString("%").concat(userDto.phone).concat("%")
    )
    userDto.phone = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun nullUsername() {
    val expression = qUser.username.like(
      Expressions.asString("%").concat(userDto.username).concat("%")
    )
    userDto.username = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun emptyUsername() {
    val expression = qUser.username.like(
      Expressions.asString("%").concat(userDto.username).concat("%")
    )
    userDto.username = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun nullRole() {
    val expression = qUser.role.eq(
      RoleType.valueOf(userDto.role!!)
    )
    userDto.role = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun emptyRole() {
    val expression = qUser.role.eq(
      RoleType.valueOf(userDto.role!!)
    )
    userDto.role = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun nullIdentificationType() {
    val expression = qUser.identificationType.eq(
      IdentificationType.valueOf(userDto.identificationType!!)
    )
    userDto.identificationType = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun emptyIdentificationType() {
    val expression = qUser.identificationType.eq(
      IdentificationType.valueOf(userDto.identificationType!!)
    )
    userDto.identificationType = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun nullNumberIdentification() {
    val expression = qUser.numberIdentification.like(
      Expressions.asString("%").concat(userDto.numberIdentification).concat("%")
    )
    userDto.numberIdentification = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun emptyNumberIdentification() {
    val expression = qUser.numberIdentification.like(
      Expressions.asString("%").concat(userDto.numberIdentification).concat("%")
    )
    userDto.numberIdentification = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun nullStreet() {
    val expression = qUser.address.street.like(
      Expressions.asString("%").concat(userDto.address!!.street).concat("%")
    )
    userDto.address!!.street = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun nullCity() {
    val expression = qUser.address.city.like(
      Expressions.asString("%").concat(userDto.address!!.city).concat("%")
    )
    userDto.address!!.city = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun nullPostalCode() {
    val expression = qUser.address.postalCode.like(
      Expressions.asString("%").concat(userDto.address!!.postalCode).concat("%")
    )
    userDto.address!!.postalCode = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun nullCountry() {
    val expression = qUser.address.country.like(
      Expressions.asString("%").concat(userDto.address!!.country).concat("%")
    )
    userDto.address!!.country = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  @Test
  fun nullBirthday() {
    val expression = qUser.birthDate.eq(userDto.birthDate)
    userDto.birthDate = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }
}