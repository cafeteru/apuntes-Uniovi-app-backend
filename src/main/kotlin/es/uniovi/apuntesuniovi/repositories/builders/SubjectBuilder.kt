package es.uniovi.apuntesuniovi.repositories.builders

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.models.QSubject
import es.uniovi.apuntesuniovi.models.types.SubjectType

/**
 * Class to create conditions to filter users
 */
class SubjectBuilder {

    /**
     * Create conditions to filter users
     */
    fun createBuilder(subjectDto: SubjectDto?): BooleanBuilder {
        val builder = BooleanBuilder()
        val qSubject = QSubject.subject
        subjectDto?.let {
            createNameFilter(it, builder, qSubject)
            createSubjectTypeFilter(it, builder, qSubject)
            if (subjectDto.active != null) {
                builder.and(qSubject.active.eq(it.active))
            }
        }
        return builder
    }

    private fun createNameFilter(
        subjectDto: SubjectDto,
        builder: BooleanBuilder,
        qSubject: QSubject
    ) {
        if (!subjectDto.name.isNullOrEmpty()) {
            builder.and(
                qSubject.name.like(
                    Expressions.asString("%").concat(subjectDto.name).concat("%")
                )
            )
        }
    }

    private fun createSubjectTypeFilter(
        subjectDto: SubjectDto,
        builder: BooleanBuilder,
        qSubject: QSubject
    ) {
        val subjectType = subjectDto.subjectType
        if (!subjectType.isNullOrEmpty()) {
            builder.and(
                qSubject.subjectType.eq(
                    SubjectType.valueOf(subjectType)
                )
            )
        }
    }
}