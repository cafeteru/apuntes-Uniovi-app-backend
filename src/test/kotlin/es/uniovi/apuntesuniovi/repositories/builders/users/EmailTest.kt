package es.uniovi.apuntesuniovi.repositories.builders.users

import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.QUser
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.builders.UserBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test class UserBuilder
 */
class EmailTest {
  private lateinit var user: User
  private lateinit var qUser: QUser

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initData() {
    user = MockUserCreator().create()
    qUser = QUser.user
  }

  /**
   * Checks conditions with null email
   */
  @Test
  fun nullEmail() {
    val expression = qUser.email.like(
      Expressions.asString("%").concat(user.email).concat("%")
    )
    user.email = null
    val builder = UserBuilder().createBuilder(user)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }
}