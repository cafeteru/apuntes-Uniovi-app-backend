package es.uniovi.apuntesuniovi.repositories.builders.users

import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.mocks.dtos.MockUserDtoCreator
import es.uniovi.apuntesuniovi.models.QUser
import es.uniovi.apuntesuniovi.repositories.builders.UserBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test class UserBuilder
 */
class PhoneTest {
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
}