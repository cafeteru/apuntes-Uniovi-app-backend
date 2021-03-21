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
 * Check the update method of the UserService class
 */
class UpdateTest {
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
    Mockito.`when`(userService.update(user.id!!, user)).thenReturn(user)
    val httpResponse = userController.update(user.id!!, user)
    Assertions.assertEquals(httpResponse.statusCode, HttpStatus.OK)
    Assertions.assertEquals(httpResponse.body, user)
  }
}