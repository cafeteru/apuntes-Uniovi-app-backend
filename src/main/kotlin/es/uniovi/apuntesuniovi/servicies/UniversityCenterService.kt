package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.servicies.dtos.entities.UniversityCenterDto

/**
 * Service to manage university center
 */
interface UniversityCenterService {
    /**
     * Saves the subject
     *
     * @param universityCenterDto UniversityCenter to save
     */
    fun create(universityCenterDto: UniversityCenterDto): List<UniversityCenterDto>
}