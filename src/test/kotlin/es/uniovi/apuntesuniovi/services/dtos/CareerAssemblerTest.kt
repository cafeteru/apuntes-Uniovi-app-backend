package es.uniovi.apuntesuniovi.services.dtos

import es.uniovi.apuntesuniovi.infrastructure.messages.CareerMessages
import es.uniovi.apuntesuniovi.mocks.dtos.MockCareerDtoCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockCareerCreator
import es.uniovi.apuntesuniovi.models.types.LanguageType
import es.uniovi.apuntesuniovi.services.dtos.assemblers.CareerAssembler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.fail
import org.mockito.junit.jupiter.MockitoExtension

/**
 * Check class CareerAssembler
 */
@ExtendWith(MockitoExtension::class)
class CareerAssemblerTest {
  private lateinit var careerAssembler: CareerAssembler

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initTest() {
    careerAssembler = CareerAssembler()
  }

  /**
   * Checks the conversion with valid Career
   */
  @Test
  fun validCareer() {
    val career = MockCareerCreator().create()
    career.addLanguage(LanguageType.ES.toString())
    val dto = careerAssembler.entityToDto(career)
    assertEquals(career.id, dto.id)
    assertEquals(career.name, dto.name)
    assertEquals(career.code, dto.code)
    assertEquals(career.yearImplantation, dto.yearImplantation)
    assertEquals(career.etcs, dto.etcs)
    assertEquals(career.languages.size, dto.languages.size)
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
    val career = careerAssembler.dtoToEntity(dto)
    assertEquals(career.id, dto.id)
    assertEquals(career.name, dto.name)
    assertEquals(career.code, dto.code)
    assertEquals(career.yearImplantation, dto.yearImplantation)
    assertEquals(career.etcs, dto.etcs)
    assertEquals(career.languages.size, dto.languages.size)
  }

  /**
   * Checks the conversion with valid CareerDto without Center
   */
  @Test
  fun validCareerDtoCenterNull() {
    val dto = MockCareerDtoCreator().create()
    val career = careerAssembler.dtoToEntity(dto)
    assertEquals(career.id, dto.id)
    assertEquals(career.name, dto.name)
    assertEquals(career.code, dto.code)
    assertEquals(career.yearImplantation, dto.yearImplantation)
    assertEquals(career.etcs, dto.etcs)
    assertEquals(career.languages.size, dto.languages.size)
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