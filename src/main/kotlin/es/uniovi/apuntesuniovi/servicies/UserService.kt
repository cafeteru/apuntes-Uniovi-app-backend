package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto

interface UserService {
    fun findAll(): List<UserDto>
    fun findByUsername(username: String): UserDto
    fun save(userDto: UserDto): List<UserDto>
}