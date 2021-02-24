package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.mocks.dtos.MockUserDtoCreator
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus


class UserControllerTest {
  private lateinit var userController: UserController
  private lateinit var userService: UserService

  @BeforeEach
  fun initTest() {
    userService = Mockito.mock(UserService::class.java)
    userController = UserController(userService)
  }

  @Test
  fun existsUser() {
    val user = MockUserDtoCreator().create();
    Mockito.`when`(userService.findById(1)).thenReturn(user)
    val httpResponse = userController.findById(1)
    assertEquals(httpResponse.statusCode, HttpStatus.OK)
    assertEquals(httpResponse.body, user)
  }
}