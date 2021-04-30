package es.uniovi.apuntesuniovi.dtos.entities

/**
 * Data Transfer Object of relationship between users and subjects
 */
data class TeachSubjectDto(
    var id: Long?,
    var subjectId: Long?,
    var teacherId: Long?
) {
    constructor() : this(null, null, null)
}