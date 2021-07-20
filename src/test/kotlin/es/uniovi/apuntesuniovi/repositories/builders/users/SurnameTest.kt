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
class SurnameTest {
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
}