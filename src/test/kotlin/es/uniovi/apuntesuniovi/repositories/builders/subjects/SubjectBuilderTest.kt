package es.uniovi.apuntesuniovi.repositories.builders.subjects

import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.mocks.dtos.MockSubjectDtoCreator
import es.uniovi.apuntesuniovi.models.QSubject
import es.uniovi.apuntesuniovi.models.types.SubjectType
import es.uniovi.apuntesuniovi.repositories.builders.SubjectBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test class SubjectBuilder
 */
class SubjectBuilderTest {
    private lateinit var subjectDto: SubjectDto
    private lateinit var qSubject: QSubject

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        subjectDto = MockSubjectDtoCreator().create()
        qSubject = QSubject.subject
    }

    /**
     * Checks conditions with null subjectDto
     */
    @Test
    fun nullAddress() {
        val builder = SubjectBuilder().createBuilder(null)
        Assertions.assertNull(builder.value)
    }

    /**
     * Checks conditions with null name
     */
    @Test
    fun nullName() {
        val expression = qSubject.name.like(
            Expressions.asString("%").concat(subjectDto.name).concat("%")
        )
        subjectDto.name = null
        val builder = SubjectBuilder().createBuilder(subjectDto)
        assertFalse(builder.value.toString().contains(expression.toString()))
    }

    /**
     * Checks conditions with null subjectType
     */
    @Test
    fun nullSubjectType() {
        val expression = qSubject.subjectType.eq(SubjectType.valueOf(subjectDto.subjectType!!))
        subjectDto.subjectType = null
        val builder = SubjectBuilder().createBuilder(subjectDto)
        assertFalse(builder.value.toString().contains(expression.toString()))
    }

    /**
     * Checks conditions with null subjectType
     */
    @Test
    fun nullActive() {
        val expression = qSubject.active.eq(subjectDto.active)
        subjectDto.active = null
        val builder = SubjectBuilder().createBuilder(subjectDto)
        assertFalse(builder.value.toString().contains(expression.toString()))
    }
}