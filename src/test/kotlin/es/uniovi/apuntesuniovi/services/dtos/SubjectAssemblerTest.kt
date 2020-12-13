package es.uniovi.apuntesuniovi.services.dtos

import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.mocks.dtos.MockSubjectDtoCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockSubjectCreator
import es.uniovi.apuntesuniovi.services.dtos.assemblers.SubjectAssembler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

/**
 * Check class SubjectDtoAssembler
 */
class SubjectAssemblerTest {
    private val subjectAssembler = SubjectAssembler()

    /**
     * Checks the conversion with valid Subject
     */
    @Test
    fun validSubject() {
        val subject = MockSubjectCreator().create()
        val dto = subjectAssembler.entityToDto(subject)
        assertEquals(subject.id, dto.id)
        assertEquals(subject.name, dto.name)
        assertEquals(subject.subjectType.toString(), dto.subjectType)
    }

    /**
     * Checks the conversion with null Subject
     */
    @Test
    fun nullSubject() {
        try {
            subjectAssembler.entityToDto(null)
            fail("Subject can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SubjectMessages.NULL)
        }
    }

    /**
     * Checks the conversion with valid SubjectDto
     */
    @Test
    fun validSubjectDto() {
        val dto = MockSubjectDtoCreator().create()
        val subject = subjectAssembler.dtoToEntity(dto)
        assertEquals(subject.id, dto.id)
        assertEquals(subject.name, dto.name)
        assertEquals(subject.subjectType.toString(), dto.subjectType)
    }

    /**
     * Checks the conversion with null SubjectDto
     */
    @Test
    fun nullSubjectDto() {
        try {
            subjectAssembler.dtoToEntity(null)
            fail("SubjectDto can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SubjectMessages.NULL)
        }
    }
}