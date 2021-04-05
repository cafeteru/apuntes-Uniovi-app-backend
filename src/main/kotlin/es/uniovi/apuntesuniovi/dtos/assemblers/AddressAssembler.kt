package es.uniovi.apuntesuniovi.dtos.assemblers

import es.uniovi.apuntesuniovi.dtos.entities.AddressDto
import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.models.Address

/**
 * Define the entity and dto conversion methods of subjects
 */
class AddressAssembler : AbstractAssembler<Address, AddressDto>() {
    override fun entityToDto(entity: Address?): AddressDto {
        logService.info("entityToDto(entity: Address) - start")
        entity?.let {
            val result = AddressDto(
                id = it.id,
                city = it.city,
                country = it.country,
                postalCode = it.postalCode,
                street = it.street
            )
            logService.info("entityToDto(entity: Address) - end")
            return result
        }
        throw IllegalArgumentException(SubjectMessages.NULL)
    }

    override fun dtoToEntity(dto: AddressDto?): Address {
        logService.info("dtoToEntity(dto: AddressDto) - start")
        dto?.let {
            val result = Address()
            result.id = it.id
            result.city = it.city
            result.country = it.country
            result.postalCode = it.postalCode
            result.street = it.street
            logService.info("dtoToEntity(dto: AddressDto) - end")
            return result
        }
        throw IllegalArgumentException(SubjectMessages.NULL)
    }
}