package es.uniovi.apuntesuniovi.services.dtos.entities

/**
 * Data Transfer Object of subjects
 */
data class SubjectDto(
  var id: Long?,
  var name: String,
  var subjectType: String?
)