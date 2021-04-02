package es.uniovi.apuntesuniovi.services.users

import com.querydsl.core.BooleanBuilder
import es.uniovi.apuntesuniovi.mocks.dtos.MockUserDtoCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.repositories.builders.UserBuilder
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


/**
 * Check find all method of the UserService class
 */
@ExtendWith(MockitoExtension::class)
class FindAllUserTest {
    private lateinit var userService: UserService

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var addressRepository: AddressRepository

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        userService = UserService(userRepository, addressRepository)
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        val user = MockUserCreator().create()
        val list = listOf(user)
        val pageable = PageRequest.of(0, 10)
        val page = PageImpl(list, pageable, list.size.toLong())
        val builder = BooleanBuilder()
        Mockito.`when`(userRepository.findAll(builder, pageable)).thenReturn(page)
        val result = userService.findAll(pageable, null)
        assertNotNull(result)
        assertEquals(result.totalElements, list.size.toLong())
        val element = result.content[0]
        assertEquals(user.id, element.id)
        assertEquals(user.username, element.username)
    }

    /**
     * Checks the functionality with valid data and filters
     */
    @Test
    fun validDataWithFilters() {
        val user = MockUserCreator().create()
        val list = listOf(user)
        val pageable = PageRequest.of(0, 10)
        val page = PageImpl(list, pageable, list.size.toLong())
        val builder = UserBuilder().createBuilder(MockUserDtoCreator().create())
        Mockito.`when`(userRepository.findAll(builder, pageable)).thenReturn(page)
        val result = userService.findAll(pageable, MockUserDtoCreator().create())
        assertNotNull(result)
        assertEquals(result.totalElements, list.size.toLong())
        val element = result.content[0]
        assertEquals(user.id, element.id)
        assertEquals(user.username, element.username)
    }
}