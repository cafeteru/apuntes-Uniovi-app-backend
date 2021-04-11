package es.uniovi.apuntesuniovi.controllers.learnSubjects

import es.uniovi.apuntesuniovi.controllers.LearnSubjectController
import es.uniovi.apuntesuniovi.mocks.dtos.MockUserDtoCreator
import es.uniovi.apuntesuniovi.services.LearnSubjectService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus

class FindStudentsBySubjectIdTest {
    private lateinit var learnSubjectController: LearnSubjectController
    private lateinit var learnSubjectService: LearnSubjectService

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        learnSubjectService = Mockito.mock(LearnSubjectService::class.java)
        learnSubjectController = LearnSubjectController(learnSubjectService)
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        val subjectDto = MockUserDtoCreator().create()
        Mockito.`when`(learnSubjectService.findStudentsBySubjectId(1)).thenReturn(listOf(subjectDto))
        val httpResponse = learnSubjectController.findStudentsBySubjectId(1)
        assertEquals(httpResponse.statusCode, HttpStatus.OK)
        assertEquals(httpResponse.body, listOf(subjectDto))
    }
}