package es.uniovi.apuntesuniovi.repositories.builders.users

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
class NameTest {
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
}