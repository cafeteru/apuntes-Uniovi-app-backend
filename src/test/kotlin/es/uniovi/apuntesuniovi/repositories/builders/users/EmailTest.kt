package es.uniovi.apuntesuniovi.repositories.builders.users

import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.mocks.entities.MockUser
import es.uniovi.apuntesuniovi.models.QUser
import es.uniovi.apuntesuniovi.repositories.builders.UserBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test class UserBuilder
 */
class EmailTest {
    private lateinit var userDto: UserDto
    private lateinit var qUser: QUser

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        userDto = Converter.convert(
            MockUser().create(),
            UserDto::class.java
        )
        qUser = QUser.user
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
}