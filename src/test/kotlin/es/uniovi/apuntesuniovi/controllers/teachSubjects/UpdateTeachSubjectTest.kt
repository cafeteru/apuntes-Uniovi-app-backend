package es.uniovi.apuntesuniovi.controllers.teachSubjects

import es.uniovi.apuntesuniovi.controllers.TeachSubjectController
import es.uniovi.apuntesuniovi.mocks.dtos.MockTeachSubjectDtoCreator
import es.uniovi.apuntesuniovi.services.TeachSubjectService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import java.util.*

class UpdateTeachSubjectTest {
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
        val subjectDto = MockTeachSubjectDtoCreator().create()
        val list = Collections.singletonList(subjectDto)
        Mockito.`when`(teachSubjectService.update(subjectDto.id!!, list)).thenReturn(list)
        val httpResponse = teachSubjectController.update(subjectDto.id!!, list)
        Assertions.assertEquals(httpResponse.statusCode, HttpStatus.OK)
        Assertions.assertEquals(httpResponse.body, list)
    }
}