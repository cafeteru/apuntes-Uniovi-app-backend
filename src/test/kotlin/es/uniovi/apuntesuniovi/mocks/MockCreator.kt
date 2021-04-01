package es.uniovi.apuntesuniovi.mocks

/**
 * Service to create mock data for testing
 */
interface MockCreator<T> {
    /**
     * Create a mock data
     */
    fun create(): T
}