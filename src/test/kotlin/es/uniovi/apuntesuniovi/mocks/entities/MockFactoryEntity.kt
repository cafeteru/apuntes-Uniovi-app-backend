package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.entities.User

/**
 * Service to create mock of all entities
 */
class MockFactoryEntity {
    /**
     * Create a mock of UserDto
     */
    fun createUser(): User {
        return MockUserCreator().create()
    }
}