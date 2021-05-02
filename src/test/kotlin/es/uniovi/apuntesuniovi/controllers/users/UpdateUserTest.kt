package es.uniovi.apuntesuniovi.controllers.users

import es.uniovi.apuntesuniovi.controllers.UserController
import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.mocks.entities.MockUser
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus

/**
 * Check the update method of the UserService class
 */
class UpdateUserTest {
    private lateinit var userDto: UserDto
    private lateinit var userController: UserController
    private lateinit var userService: UserService

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        userService = Mockito.mock(UserService::class.java)
        userController = UserController(userService)
        userDto = Converter.convert(
            MockUser().create(),
            UserDto::class.java
        )
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        Mockito.`when`(userService.update(userDto.id!!, userDto)).thenReturn(userDto)
        val httpResponse = userController.update(userDto.id!!, userDto)
        Assertions.assertEquals(httpResponse.statusCode, HttpStatus.OK)
        Assertions.assertEquals(httpResponse.body, userDto)
    }
}