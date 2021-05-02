package es.uniovi.apuntesuniovi.controllers.learnSubjects

import es.uniovi.apuntesuniovi.controllers.LearnSubjectController
import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.mocks.entities.MockUser
import es.uniovi.apuntesuniovi.services.LearnSubjectService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus


class FindStudentsBySubjectIdTest {
    private lateinit var userDto: UserDto
    private lateinit var learnSubjectController: LearnSubjectController
    private lateinit var learnSubjectService: LearnSubjectService

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        learnSubjectService = Mockito.mock(LearnSubjectService::class.java)
        learnSubjectController = LearnSubjectController(learnSubjectService)
        userDto = Converter.Companion.convert(
            MockUser().createStudent(), UserDto::class.java
        )
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        val list = listOf(userDto)
        val pageable = PageRequest.of(0, 10)
        val page = PageImpl(list, pageable, list.size.toLong())
        Mockito.`when`(learnSubjectService.findStudentsBySubjectId(1, pageable)).thenReturn(page)
        val httpResponse = learnSubjectController.findStudentsBySubjectId(1, pageable)
        assertEquals(httpResponse.statusCode, HttpStatus.OK)
        assertEquals(httpResponse.body, page)
    }
}