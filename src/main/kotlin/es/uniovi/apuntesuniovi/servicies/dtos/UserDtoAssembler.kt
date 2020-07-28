package es.uniovi.apuntesuniovi.servicies.dtos

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.servicies.dtos.entities.PersonDto

interface PersonDtoAssembler {
    fun entityToDto(entity: User): PersonDto
    fun dtoToEntity(dto: PersonDto): User
    fun listToDto(entityList: List<User>): List<PersonDto>
    fun listToEntities(dtoList: List<PersonDto>): List<User>
}