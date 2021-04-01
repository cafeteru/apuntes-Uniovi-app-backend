package es.uniovi.apuntesuniovi.controllers.users

import es.uniovi.apuntesuniovi.controllers.UserController
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.mocks.dtos.MockUserDtoCreator
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

/**
 * Check find all method of the UserController class
 */
class FindAllUsersTest {
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
    fun findAllTest() {
        val userDto = MockUserDtoCreator().create()
        val list: List<UserDto> = listOf(userDto)
        val pageable = PageRequest.of(0, 5)
        val page: Page<UserDto> = PageImpl(list, pageable, 1)
        Mockito.`when`(userService.findAll(pageable, userDto)).thenReturn(page)
        val httpResponse: ResponseEntity<Page<UserDto>> = userController.findAll(pageable, userDto)
        Assertions.assertEquals(httpResponse.statusCode, HttpStatus.OK)
        Assertions.assertEquals(httpResponse.body, page)
    }

    @Test
    fun findAllEmptyTest() {
        val pageable = PageRequest.of(0, 5)
        val page: Page<UserDto> = PageImpl(ArrayList(), pageable, 1)
        val userDto = MockUserDtoCreator().create()
        Mockito.`when`(userService.findAll(pageable, userDto)).thenReturn(page)
        val httpResponse: ResponseEntity<Page<UserDto>> =
            userController.findAll(pageable, userDto)
        Assertions.assertEquals(httpResponse.statusCode, HttpStatus.NO_CONTENT)
    }
}