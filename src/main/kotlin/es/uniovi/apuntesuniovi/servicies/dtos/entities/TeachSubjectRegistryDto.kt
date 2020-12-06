package es.uniovi.apuntesuniovi.servicies.dtos.entities

import java.time.LocalDate

/**
 * Data Transfer Object of registries of teach
 */
data class TeachSubjectRegistryDto(
    var id: Long?,
    var teachSubjectId: Long,
    var initDay: LocalDate,
    var finishDay: LocalDate?,
)
