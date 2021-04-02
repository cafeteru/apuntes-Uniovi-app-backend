package es.uniovi.apuntesuniovi.controllers.users

import es.uniovi.apuntesuniovi.controllers.UserController
import es.uniovi.apuntesuniovi.mocks.dtos.MockUserDtoCreator
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus

/**
 * Check find by id method of the UserController class
 */
class FindUserByIdTest {
    private lateinit var userController: UserController
    private lateinit var userService: UserService

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        userService = Mockito.mock(UserService::class.java)
        userController = UserController(userService)
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        val userDto = MockUserDtoCreator().create()
        Mockito.`when`(userService.findById(1)).thenReturn(userDto)
        val httpResponse = userController.findById(1)
        Assertions.assertEquals(httpResponse.statusCode, HttpStatus.OK)
        Assertions.assertEquals(httpResponse.body, userDto)
    }
}