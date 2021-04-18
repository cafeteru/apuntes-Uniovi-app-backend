package es.uniovi.apuntesuniovi.controllers.learnSubjects

import es.uniovi.apuntesuniovi.controllers.LearnSubjectController
import es.uniovi.apuntesuniovi.mocks.dtos.MockLearnSubjectDtoCreator
import es.uniovi.apuntesuniovi.services.LearnSubjectService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus

class CreateLearnSubjectTest {
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
        val id = 1L
        val dto = MockLearnSubjectDtoCreator().create()
        Mockito.`when`(
            learnSubjectService.create(id, listOf(dto))
        ).thenReturn(
            listOf(dto)
        )
        val httpResponse = learnSubjectController.create(id, listOf(dto))
        Assertions.assertEquals(httpResponse.statusCode, HttpStatus.OK)
        Assertions.assertEquals(httpResponse.body, listOf(dto))
    }
}