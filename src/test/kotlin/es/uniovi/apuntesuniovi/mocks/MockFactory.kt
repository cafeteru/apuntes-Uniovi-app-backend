package es.uniovi.apuntesuniovi.mocks

import es.uniovi.apuntesuniovi.mocks.dtos.MockFactoryDto
import es.uniovi.apuntesuniovi.mocks.entities.MockFactoryEntity

/**
 * Service to manage mock data for testing
 */
class MockFactory {
    /**
     * Return the mock factory of dtos
     */
    fun getDtos(): MockFactoryDto {
        return MockFactoryDto()
    }

    /**
     * Return the mock factory of entities
     */
    fun getEntities(): MockFactoryEntity {
        return MockFactoryEntity()
    }
}