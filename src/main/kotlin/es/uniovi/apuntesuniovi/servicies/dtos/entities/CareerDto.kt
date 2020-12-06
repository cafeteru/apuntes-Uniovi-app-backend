package es.uniovi.apuntesuniovi.servicies.dtos.entities

/**
 * Data Transfer Object of careers
 */
data class CareerDto(
    var id: Long?,
    var name: String,
    var code: String? = null,
    var yearImplantation: String? = null,
    var typeTeaching: String? = null,
    var ISCED: String? = null,
    var ECTS: Int? = null,
    var languages: String? = null,
    var centerId: Long? = null,
    var configurationECTSId: Long? = null
)
