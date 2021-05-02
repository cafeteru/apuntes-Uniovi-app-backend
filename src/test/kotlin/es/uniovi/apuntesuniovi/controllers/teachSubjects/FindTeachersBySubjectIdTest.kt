package es.uniovi.apuntesuniovi.controllers.teachSubjects

import es.uniovi.apuntesuniovi.controllers.TeachSubjectController
import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.mocks.entities.MockUser
import es.uniovi.apuntesuniovi.services.TeachSubjectService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

/**
 * Check find teachers by subject´s id method of the TeachSubjectController class
 */
class FindTeachersBySubjectIdTest {
    private lateinit var teachSubjectController: TeachSubjectController
    private lateinit var teachSubjectService: TeachSubjectService
    private lateinit var userDto: UserDto

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        teachSubjectService = Mockito.mock(TeachSubjectService::class.java)
        teachSubjectController = TeachSubjectController(teachSubjectService)
        userDto = Converter.Companion.convert(
            MockUser().createTeacher(),
            UserDto::class.java
        )
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        Mockito.`when`(teachSubjectService.findTeachersBySubjectId(1)).thenReturn(listOf(userDto))
        val list = teachSubjectController.findTeachersBySubjectId(1)
        assertEquals(list, listOf(userDto))
    }
}