package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.Person
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.dates.DateService
import es.uniovi.apuntesuniovi.servicies.dtos.PersonDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.PersonDto
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@Service
class PersonAssemblerDtoImpl : PersonDtoAssembler {
    private val logService = LogService(this.javaClass)

    override fun entityToDto(entity: Person): PersonDto {
        logService.info("entityToDto(entity: ${entity}) - start")
        val result = PersonDto(
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
        logService.info("entityToDto(entity: ${entity}) - end")
        return result
    }

    override fun dtoToEntity(dto: PersonDto): Person {
        logService.info("dtoToEntity(dto: ${dto}) - start")
        val result = Person(id = dto.id,
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
        logService.info("dtoToEntity(dto: ${dto}) - end")
        return result
    }

    override fun listToDto(entityList: List<Person>): List<PersonDto> {
        logService.info("listToDto(entityList: ${entityList}) - start")
        val dtoList: MutableList<PersonDto> = ArrayList()
        entityList.forEach(Consumer { entity: Person -> dtoList.add(entityToDto(entity)) })
        logService.info("listToDto(entityList: ${entityList}) - end")
        return dtoList
    }

    override fun listToEntities(dtoList: List<PersonDto>): List<Person> {
        logService.info("listToEntities(dtoList: ${dtoList}) - start")
        val entityList: MutableList<Person> = ArrayList()
        dtoList.forEach(Consumer { dto: PersonDto -> entityList.add(dtoToEntity(dto)) })
        logService.info("listToEntities(dtoList: ${dtoList}) - end")
        return entityList
    }
}