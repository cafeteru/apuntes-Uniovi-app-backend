package es.uniovi.apuntesuniovi.services.unitSubject

import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.UnitSubjectDto
import es.uniovi.apuntesuniovi.infrastructure.messages.UnitSubjectMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUnitSubject
import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.repositories.UnitSubjectRepository
import es.uniovi.apuntesuniovi.services.UnitSubjectService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals
import kotlin.test.fail

/**
 * Check the creation method of the UnitSubjectService class
 */
@ExtendWith(MockitoExtension::class)
class CreateUnitSubjectTest {
    private lateinit var unitSubject: UnitSubject
    private lateinit var unitSubjectDto: UnitSubjectDto
    private lateinit var unitSubjectService: UnitSubjectService

    @Mock
    private lateinit var unitSubjectRepository: UnitSubjectRepository

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        unitSubjectService = UnitSubjectService(unitSubjectRepository)
        unitSubject = MockUnitSubject().create()
        unitSubjectDto = Converter.convert(unitSubject, UnitSubjectDto::class.java)
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        Mockito.`when`(
            unitSubjectRepository.save(Mockito.any(UnitSubject::class.java))
        ).thenReturn(unitSubject)
        val result = unitSubjectService.create(unitSubjectDto)
        assertEquals(unitSubjectDto, result)
        assertEquals(unitSubject.id, result.id)
    }

    @Test
    fun emptyName() {
        try {
            unitSubjectDto.name = ""
            unitSubjectService.create(unitSubjectDto)
            fail(UnitSubjectMessages.LIMIT_NAME)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UnitSubjectMessages.LIMIT_NAME)
        }
    }

    @Test
    fun nullName() {
        try {
            unitSubjectDto.name = null
            unitSubjectService.create(unitSubjectDto)
            fail(UnitSubjectMessages.NULL_NAME)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UnitSubjectMessages.NULL_NAME)
        }
    }

    @Test
    fun nullSubjectId() {
        try {
            unitSubjectDto.subjectId = null
            unitSubjectService.create(unitSubjectDto)
            fail(UnitSubjectMessages.INVALID_SUBJECT_ID)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UnitSubjectMessages.INVALID_SUBJECT_ID)
        }
    }


    @Test
    fun existsUnitSubject() {
        try {
            Mockito.`when`(
                unitSubjectRepository.existsByNameAndSubjectId(
                    unitSubject.name!!, unitSubject.subject!!.id!!
                )
            ).thenReturn(true)
            unitSubjectService.create(unitSubjectDto)
            fail(UnitSubjectMessages.EXISTS_NAME)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UnitSubjectMessages.EXISTS_NAME)
        }
    }
}