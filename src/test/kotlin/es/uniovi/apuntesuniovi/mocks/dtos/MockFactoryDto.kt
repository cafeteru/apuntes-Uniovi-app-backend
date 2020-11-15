package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto

/**
 * Service to create mock of all dtos
 */
class MockFactoryDto {
    /**
     * Create a mock of UserDto
     */
    fun createUserDto(): UserDto {
        return MockUserDtoCreator().create();
    }
}