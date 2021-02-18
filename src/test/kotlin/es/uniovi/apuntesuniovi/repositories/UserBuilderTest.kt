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

  /**
   * Checks conditions with null name
   */
  @Test
  fun nullName() {
    val expression = qUser.name.like(
      Expressions.asString("%").concat(userDto.name).concat("%")
    )
    userDto.name = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with empty name
   */
  @Test
  fun emptyName() {
    val expression = qUser.name.like(
      Expressions.asString("%").concat(userDto.name).concat("%")
    )
    userDto.name = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with null surname
   */
  @Test
  fun nullSurname() {
    val expression = qUser.surname.like(
      Expressions.asString("%").concat(userDto.surname).concat("%")
    )
    userDto.surname = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with empty surname
   */
  @Test
  fun emptySurname() {
    val expression = qUser.surname.like(
      Expressions.asString("%").concat(userDto.surname).concat("%")
    )
    userDto.surname = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with null email
   */
  @Test
  fun nullEmail() {
    val expression = qUser.email.like(
      Expressions.asString("%").concat(userDto.email).concat("%")
    )
    userDto.email = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with empty email
   */
  @Test
  fun emptyEmail() {
    val expression = qUser.email.like(
      Expressions.asString("%").concat(userDto.email).concat("%")
    )
    userDto.email = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with null phone
   */
  @Test
  fun nullPhone() {
    val expression = qUser.phone.like(
      Expressions.asString("%").concat(userDto.phone).concat("%")
    )
    userDto.phone = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with empty phone
   */
  @Test
  fun emptyPhone() {
    val expression = qUser.phone.like(
      Expressions.asString("%").concat(userDto.phone).concat("%")
    )
    userDto.phone = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with null username
   */
  @Test
  fun nullUsername() {
    val expression = qUser.username.like(
      Expressions.asString("%").concat(userDto.username).concat("%")
    )
    userDto.username = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with empty username
   */
  @Test
  fun emptyUsername() {
    val expression = qUser.username.like(
      Expressions.asString("%").concat(userDto.username).concat("%")
    )
    userDto.username = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with null role
   */
  @Test
  fun nullRole() {
    val expression = qUser.role.eq(
      RoleType.valueOf(userDto.role!!)
    )
    userDto.role = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with empty role
   */
  @Test
  fun emptyRole() {
    val expression = qUser.role.eq(
      RoleType.valueOf(userDto.role!!)
    )
    userDto.role = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with null identificationType
   */
  @Test
  fun nullIdentificationType() {
    val expression = qUser.identificationType.eq(
      IdentificationType.valueOf(userDto.identificationType!!)
    )
    userDto.identificationType = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with empty identificationType
   */
  @Test
  fun emptyIdentificationType() {
    val expression = qUser.identificationType.eq(
      IdentificationType.valueOf(userDto.identificationType!!)
    )
    userDto.identificationType = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with null numberIdentification
   */
  @Test
  fun nullNumberIdentification() {
    val expression = qUser.numberIdentification.like(
      Expressions.asString("%").concat(userDto.numberIdentification).concat("%")
    )
    userDto.numberIdentification = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with empty numberIdentification
   */
  @Test
  fun emptyNumberIdentification() {
    val expression = qUser.numberIdentification.like(
      Expressions.asString("%").concat(userDto.numberIdentification).concat("%")
    )
    userDto.numberIdentification = ""
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }


  /**
   * Checks conditions with null birthDate
   */
  @Test
  fun nullBirthday() {
    val expression = qUser.birthDate.eq(userDto.birthDate)
    userDto.birthDate = null
    val builder = UserBuilder().createBuilder(userDto)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }
}