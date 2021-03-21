package es.uniovi.apuntesuniovi.controllers.users

import es.uniovi.apuntesuniovi.controllers.UserController
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus

/**
 * Check the creation method of the UserController class
 */
class CreateTest {
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
    val user = MockUserCreator().create()
    Mockito.`when`(userService.create(user)).thenReturn(user)
    val httpResponse = userController.create(user)
    Assertions.assertEquals(httpResponse.statusCode, HttpStatus.OK)
    Assertions.assertEquals(httpResponse.body, user)
  }
}