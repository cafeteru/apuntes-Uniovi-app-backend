package es.uniovi.apuntesuniovi.controllers.subjects

import es.uniovi.apuntesuniovi.controllers.SubjectController
import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.mocks.entities.MockSubject
import es.uniovi.apuntesuniovi.services.SubjectService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus

/**
 * Check disable method of the SubjectController class
 */
class DisableSubjectTest {
    private lateinit var subjectController: SubjectController
    private lateinit var subjectService: SubjectService
    private lateinit var subjectDto: SubjectDto

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        subjectService = Mockito.mock(SubjectService::class.java)
        subjectController = SubjectController(subjectService)
        subjectDto = Converter.convert(
            MockSubject().create(),
            SubjectDto::class.java
        )
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        Mockito.`when`(subjectService.disable(subjectDto.id!!, true)).thenReturn(subjectDto)
        val httpResponse = subjectController.disable(subjectDto.id!!, true)
        assertEquals(httpResponse.statusCode, HttpStatus.OK)
        assertEquals(httpResponse.body, subjectDto)
    }
}