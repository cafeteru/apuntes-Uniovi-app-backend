package es.uniovi.apuntesuniovi.repositories.builders.users

import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.mocks.entities.MockUser
import es.uniovi.apuntesuniovi.models.QUser
import es.uniovi.apuntesuniovi.models.types.IdentificationType
import es.uniovi.apuntesuniovi.repositories.builders.UserBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test class UserBuilder
 */
class IdentificationTypeTest {
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