package es.uniovi.apuntesuniovi.services.subjects

import com.querydsl.core.BooleanBuilder
import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.mocks.entities.MockSubject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.builders.SubjectBuilder
import es.uniovi.apuntesuniovi.services.SubjectService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


/**
 * Check find all method of the SubjectService class
 */
@ExtendWith(MockitoExtension::class)
class FindAllSubjectsTest {
    private lateinit var subjectDto: SubjectDto
    private lateinit var subjectService: SubjectService

    @Mock
    private lateinit var subjectRepository: SubjectRepository

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        subjectService = SubjectService(subjectRepository)
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
        val subject = MockSubject().create()
        val list = listOf(subject)
        val pageable = PageRequest.of(0, 10)
        val page = PageImpl(list, pageable, list.size.toLong())
        val builder = BooleanBuilder()
        Mockito.`when`(subjectRepository.findAll(builder, pageable)).thenReturn(page)
        val result = subjectService.findAll(pageable, null)
        assertNotNull(result)
        assertEquals(result.totalElements, list.size.toLong())
        val element = result.content[0]
        assertEquals(subject.id, element.id)
    }

    /**
     * Checks the functionality with valid data and filters
     */
    @Test
    fun validDataWithFilters() {
        val subject = MockSubject().create()
        val list = listOf(subject)
        val pageable = PageRequest.of(0, 10)
        val page = PageImpl(list, pageable, list.size.toLong())
        val builder = SubjectBuilder().createBuilder(subjectDto)
        Mockito.`when`(subjectRepository.findAll(builder, pageable)).thenReturn(page)
        val result = subjectService.findAll(pageable, subjectDto)
        assertNotNull(result)
        assertEquals(result.totalElements, list.size.toLong())
        val element = result.content[0]
        assertEquals(subject.id, element.id)
    }
}