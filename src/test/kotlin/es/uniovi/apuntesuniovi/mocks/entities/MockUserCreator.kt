package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.mocks.MockCreator
import java.time.LocalDate

/**
 * Service to create mock data of the entity User
 */
class MockUserCreator: MockCreator<User> {
    override fun create(): User {
        val user = User()
        user.id = 1
        user.name = "Test"
        user.surname = "Test"
        user.active = true
        user.birthDate = LocalDate.now()
        user.email = "test@test.es" // TODO validatorEmail
        user.identificationType = IdentificationType.NIE
        user.numberIdentification = "03203911B"
        user.username = "test"
        user.role = RoleType.STUDENT
        return user
    }
}