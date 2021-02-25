package es.uniovi.apuntesuniovi.controllers.users

import es.uniovi.apuntesuniovi.controllers.UserController
import es.uniovi.apuntesuniovi.mocks.dtos.MockUserDtoCreator
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus

class UpdateTest {
  private lateinit var userController: UserController
  private lateinit var userService: UserService

  @BeforeEach
  fun initTest() {
    userService = Mockito.mock(UserService::class.java)
    userController = UserController(userService)
  }

  @Test
  fun validData() {
    val userDto = MockUserDtoCreator().create()
    Mockito.`when`(userService.update(userDto.id!!, userDto)).thenReturn(userDto)
    val httpResponse = userController.update(userDto.id!!, userDto)
    Assertions.assertEquals(httpResponse.statusCode, HttpStatus.OK)
    Assertions.assertEquals(httpResponse.body, userDto)
  }
}