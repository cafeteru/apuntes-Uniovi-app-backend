package es.uniovi.apuntesuniovi.repositories.builders.users

import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.mocks.dtos.MockUserDtoCreator
import es.uniovi.apuntesuniovi.models.QUser
import es.uniovi.apuntesuniovi.repositories.builders.UserBuilder
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test class UserBuilder
 */
class NumberIdentificationTest {
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
}