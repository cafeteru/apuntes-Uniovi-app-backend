package es.uniovi.apuntesuniovi.dtos.entities

/**
 * Data Transfer Object of unit of subjects
 */
data class UnitSubjectDto(
    var id: Long?,
    var name: String?,
    var position: Int?,
    var subjectId: Long?,
) {
    constructor() : this(null, null, null, null)
}
