package es.uniovi.apuntesuniovi.services.dtos

import es.uniovi.apuntesuniovi.infrastructure.messages.CareerMessages
import es.uniovi.apuntesuniovi.mocks.dtos.MockCareerDtoCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockCareerCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockCenterCreator
import es.uniovi.apuntesuniovi.models.types.LanguageType
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.services.dtos.assemblers.CareerAssembler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.fail
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertNull

/**
 * Check class CareerAssembler
 */
@ExtendWith(MockitoExtension::class)
class CareerAssemblerTest {
    @Mock
    private lateinit var centerRepository: CenterRepository

    private lateinit var careerAssembler: CareerAssembler

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        careerAssembler = CareerAssembler(centerRepository)
    }

    /**
     * Checks the conversion with valid Career
     */
    @Test
    fun validCareer() {
        val career = MockCareerCreator().create()
        career.addLanguage(LanguageType.SPANISH.toString())
        val dto = careerAssembler.entityToDto(career)
        assertEquals(career.id, dto.id)
        assertEquals(career.name, dto.name)
        assertEquals(career.code, dto.code)
        assertEquals(career.yearImplantation, dto.yearImplantation)
        assertEquals(career.etcs, dto.etcs)
        assertEquals(career.languages.size, dto.languages.size)
        assertEquals(career.center?.id, dto.centerId)
    }

    /**
     * Checks the conversion with null Career
     */
    @Test
    fun nullCareer() {
        try {
            careerAssembler.entityToDto(null)
            fail("Career can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, CareerMessages.NULL)
        }
    }

    /**
     * Checks the conversion with valid CareerDto
     */
    @Test
    fun validCareerDto() {
        val dto = MockCareerDtoCreator().create()
        Mockito.`when`(centerRepository.findById(dto.centerId!!)).thenReturn(
            Optional.of(MockCenterCreator().create())
        )
        val career = careerAssembler.dtoToEntity(dto)
        assertEquals(career.id, dto.id)
        assertEquals(career.name, dto.name)
        assertEquals(career.code, dto.code)
        assertEquals(career.yearImplantation, dto.yearImplantation)
        assertEquals(career.etcs, dto.etcs)
        assertEquals(career.languages.size, dto.languages.size)
        assertEquals(career.center?.id, dto.centerId)
    }

    /**
     * Checks the conversion with valid CareerDto without Center
     */
    @Test
    fun validCareerDtoCenterNull() {
        val dto = MockCareerDtoCreator().create()
        dto.centerId = null
        val career = careerAssembler.dtoToEntity(dto)
        assertEquals(career.id, dto.id)
        assertEquals(career.name, dto.name)
        assertEquals(career.code, dto.code)
        assertEquals(career.yearImplantation, dto.yearImplantation)
        assertEquals(career.etcs, dto.etcs)
        assertEquals(career.languages.size, dto.languages.size)
        assertNull(career.center)
        assertNull(dto.centerId)
    }


    /**
     * Checks the conversion with null CareerDto
     */
    @Test
    fun nullCareerDto() {
        try {
            careerAssembler.dtoToEntity(null)
            fail("CareerDto can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, CareerMessages.NULL)
        }
    }
}