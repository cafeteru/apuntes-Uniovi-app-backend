package es.uniovi.apuntesuniovi.controllers.learnSubjects

import es.uniovi.apuntesuniovi.controllers.LearnSubjectController
import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.LearnSubjectDto
import es.uniovi.apuntesuniovi.mocks.entities.MockLearnSubject
import es.uniovi.apuntesuniovi.services.LearnSubjectService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus

class CreateLearnSubjectTest {
    private lateinit var learnSubjectDto: LearnSubjectDto
    private lateinit var learnSubjectController: LearnSubjectController
    private lateinit var learnSubjectService: LearnSubjectService

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        learnSubjectService = Mockito.mock(LearnSubjectService::class.java)
        learnSubjectController = LearnSubjectController(learnSubjectService)
        learnSubjectDto = Converter.convert(
            MockLearnSubject().create(), LearnSubjectDto::class.java
        )
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        Mockito.`when`(
            learnSubjectService.create(learnSubjectDto.id!!, listOf(learnSubjectDto))
        ).thenReturn(
            listOf(learnSubjectDto)
        )
        val httpResponse = learnSubjectController.create(learnSubjectDto.id!!, listOf(learnSubjectDto))
        Assertions.assertEquals(httpResponse.statusCode, HttpStatus.OK)
        Assertions.assertEquals(httpResponse.body, listOf(learnSubjectDto))
    }
}