package es.uniovi.apuntesuniovi.servicies.dtos

import es.uniovi.apuntesuniovi.entities.Person
import es.uniovi.apuntesuniovi.servicies.dtos.entities.PersonDto

interface PersonDtoAssembler {
    fun entityToDto(entity: Person): PersonDto
    fun dtoToEntity(dto: PersonDto): Person
    fun listToDto(entityList: List<Person>): List<PersonDto>
    fun listToEntities(dtoList: List<PersonDto>): List<Person>
}