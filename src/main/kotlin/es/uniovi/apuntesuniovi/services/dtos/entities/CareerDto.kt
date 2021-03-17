package es.uniovi.apuntesuniovi.services.dtos.entities

/**
 * Data Transfer Object of careers
 */
data class CareerDto(
  var id: Long?,
  var name: String,
  var code: String? = null,
  var yearImplantation: Int? = null,
  var etcs: Int? = null,
  val languages: List<String> = ArrayList(),
)
