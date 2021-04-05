package es.uniovi.apuntesuniovi.controllers.teachSubjects

import es.uniovi.apuntesuniovi.controllers.TeachSubjectController
import es.uniovi.apuntesuniovi.mocks.dtos.MockTeachSubjectDtoCreator
import es.uniovi.apuntesuniovi.services.TeachSubjectService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus

/**
 * Check the creation method of the SubjectController class
 */
class CreateTeachSubjectTest {
    private lateinit var teachSubjectController: TeachSubjectController
    private lateinit var teachSubjectService: TeachSubjectService

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        teachSubjectService = Mockito.mock(TeachSubjectService::class.java)
        teachSubjectController = TeachSubjectController(teachSubjectService)
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        val dto = MockTeachSubjectDtoCreator().create()
        Mockito.`when`(
            teachSubjectService.create(
                listOf(dto)
            )
        ).thenReturn(
            listOf(dto)
        )
        val httpResponse = teachSubjectController.create(listOf(dto))
        Assertions.assertEquals(httpResponse.statusCode, HttpStatus.OK)
        Assertions.assertEquals(httpResponse.body, listOf(dto))
    }
}