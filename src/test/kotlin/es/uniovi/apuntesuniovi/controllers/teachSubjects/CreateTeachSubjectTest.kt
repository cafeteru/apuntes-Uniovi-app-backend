package es.uniovi.apuntesuniovi.controllers.teachSubjects

import es.uniovi.apuntesuniovi.controllers.TeachSubjectController
import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.TeachSubjectDto
import es.uniovi.apuntesuniovi.mocks.entities.MockSubject
import es.uniovi.apuntesuniovi.services.TeachSubjectService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

/**
 * Check the creation method of the SubjectController class
 */
class CreateTeachSubjectTest {
    private lateinit var teachSubjectController: TeachSubjectController
    private lateinit var teachSubjectService: TeachSubjectService
    private lateinit var teachSubjectDto: TeachSubjectDto

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        teachSubjectService = Mockito.mock(TeachSubjectService::class.java)
        teachSubjectController = TeachSubjectController(teachSubjectService)
        teachSubjectDto = Converter.convert(
            MockSubject().create(),
            TeachSubjectDto::class.java
        )
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        Mockito.`when`(
            teachSubjectService.create(teachSubjectDto.id!!, listOf(teachSubjectDto))
        ).thenReturn(listOf(teachSubjectDto))
        val list = teachSubjectController.create(teachSubjectDto.id!!, listOf(teachSubjectDto))
        Assertions.assertEquals(list, listOf(teachSubjectDto))
    }
}