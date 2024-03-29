package es.uniovi.apuntesuniovi.dtos.entities

/**
 * Data Transfer Object of subjects
 */
data class SubjectDto(
    var id: Long?,
    var name: String?,
    var subjectType: String?,
    var active: Boolean?
) {
    constructor() : this(null, null, null, null)
}