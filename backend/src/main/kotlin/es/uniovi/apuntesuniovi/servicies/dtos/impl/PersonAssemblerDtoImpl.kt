package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.Person
import es.uniovi.apuntesuniovi.servicies.dates.DateService
import es.uniovi.apuntesuniovi.servicies.dtos.PersonDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.PersonDto
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@Service
class PersonAssemblerDtoImpl : PersonDtoAssembler {
    override fun entityToDto(entity: Person): PersonDto {
        return PersonDto(
                id = entity.id,
                name = entity.name,
                surname = entity.surname,
                email = entity.email,
                phone = entity.phone,
                active = entity.active,
                img = entity.img,
                birthDate = DateService.dateToString(entity.birthDate),
                username = entity.username,
                password = entity.password,
                role = entity.role.toString(),
                identificationType = entity.identificationType.toString(),
                numberIdentification = entity.numberIdentification)
    }

    override fun dtoToEntity(dto: PersonDto): Person {
        return Person(id = dto.id,
                name = dto.name,
                surname = dto.surname,
                email = dto.email,
                phone = dto.phone,
                active = dto.active,
                img = dto.img,
                birthDate = dto.birthDate,
                username = dto.username,
                password = dto.password,
                role = dto.role,
                identificationType = dto.identificationType,
                numberIdentification = dto.numberIdentification)
    }

    override fun listToDto(entityList: List<Person>): List<PersonDto> {
        val dtoList: MutableList<PersonDto> = ArrayList<PersonDto>()
        entityList.forEach(Consumer { entity: Person -> dtoList.add(entityToDto(entity)) })
        return dtoList
    }

    override fun listToEntities(dtoList: List<PersonDto>): List<Person> {
        val entityList: MutableList<Person> = ArrayList<Person>()
        dtoList.forEach(Consumer { dto: PersonDto -> entityList.add(dtoToEntity(dto)) })
        return entityList
    }
}