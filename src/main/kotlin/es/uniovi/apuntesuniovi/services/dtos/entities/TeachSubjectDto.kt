package es.uniovi.apuntesuniovi.services.dtos.entities

/**
 * Data Transfer Object of relationship between users and subjects
 */
data class TeachSubjectDto(
    var id: Long?,
    var isCoordinator: Boolean,
    var subjectId: Long,
    var teacherId: Long
)