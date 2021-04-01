package es.uniovi.apuntesuniovi.controllers.users

import es.uniovi.apuntesuniovi.controllers.UserController
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import java.security.Principal

/**
 * Check the change of language method of the UserController class
 */
class ChangeLanguageTest {
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
        val principal = Mockito.mock(Principal::class.java)
        val language = "es"
        Mockito.`when`(principal.name).thenReturn("test")
        Mockito.`when`(userService.changeLanguage(principal.name, language)).thenReturn(true)
        val httpResponse = userController.changeLanguage(language, principal)
        Assertions.assertEquals(httpResponse.statusCode, HttpStatus.OK)
    }

    /**
     * Checks the functionality with invalid data
     */
    @Test
    fun invalidData() {
        val mockPrincipal = Mockito.mock(Principal::class.java)
        val language = "test"
        Mockito.`when`(mockPrincipal.name).thenReturn("test")
        Mockito.`when`(userService.changeLanguage(mockPrincipal.name, language)).thenReturn(false)
        val httpResponse = userController.changeLanguage(language, mockPrincipal)
        Assertions.assertEquals(httpResponse.statusCode, HttpStatus.BAD_REQUEST)
    }
}