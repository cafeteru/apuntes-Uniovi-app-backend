package es.uniovi.apuntesuniovi.servicies.dtos.entities

data class TeachSubjectDto(
        var id: Long,
        var isCoordinator: Boolean,
        var subjectId: Long?,
        var teacherId: Long?
)