package es.uniovi.apuntesuniovi.controllers.users

import es.uniovi.apuntesuniovi.controllers.UserController
import es.uniovi.apuntesuniovi.mocks.dtos.MockUserDtoCreator
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus

/**
 * Check find all method of the UserController class
 */
class FindAllTest {
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
    val list = ArrayList<UserDto>()
    list.add(userDto)
    val pageable = PageRequest.of(0, 5)
    val page = PageImpl(list, pageable, 1)
    Mockito.`when`(userService.findAll(pageable, null)).thenReturn(page)
    val httpResponse = userController.findAll(pageable, null)
    Assertions.assertEquals(httpResponse.statusCode, HttpStatus.OK)
    Assertions.assertEquals(httpResponse.body, page)
  }
}