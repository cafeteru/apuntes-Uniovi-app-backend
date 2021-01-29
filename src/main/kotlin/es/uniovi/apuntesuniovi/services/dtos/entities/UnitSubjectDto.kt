package es.uniovi.apuntesuniovi.services.dtos.entities

/**
 * Data Transfer Object of unit of subjects
 */
data class UnitSubjectDto(
  var id: Long?,
  var name: String?,
  var subjectId: Long?,
  var position: Int?
)
