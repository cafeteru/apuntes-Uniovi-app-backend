package es.uniovi.apuntesuniovi.dtos.entities

/**
 * Data Transfer Object of unit of subjects
 */
data class UnitSubjectDto(
    var id: Long?,
    var name: String?,
    var subjectId: Long?,
    var position: Int?
)
