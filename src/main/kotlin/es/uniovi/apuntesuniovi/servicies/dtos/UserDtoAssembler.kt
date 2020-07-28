package es.uniovi.apuntesuniovi.servicies.dtos

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto

interface UserDtoAssembler {
    fun entityToDto(entity: User): UserDto
    fun dtoToEntity(dto: UserDto): User
    fun listToDto(entityList: List<User>): List<UserDto>
    fun listToEntities(dtoList: List<UserDto>): List<User>
}