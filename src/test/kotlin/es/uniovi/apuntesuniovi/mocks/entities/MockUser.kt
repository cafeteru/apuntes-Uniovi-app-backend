package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.models.types.IdentificationType
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.util.RandomMethods
import java.time.LocalDate

/**
 * Service to create mock data of the entity User
 */
class MockUser : MockCreator<User> {
    override fun create(): User {
        val user = User()
        user.id = 1
        user.name = "Test"
        user.surname = "Test"
        user.active = true
        user.birthDate = LocalDate.now()
        user.email = "test@test.es"
        user.identificationType = IdentificationType.DNI
        user.numberIdentification = RandomMethods.dni()
        user.username = RandomMethods.randomUsername()
        user.password = "testPassword"
        user.role = RoleType.ROLE_ADMIN
        user.phone = "623548956"
        user.img = "img"
        user.address = MockAddress().create()
        return user
    }

    /**
     * Create a mock User without id
     */
    fun createWithoutId(): User {
        val user = create()
        user.id = null
        return user
    }

    /**
     * Create a mock Teacher
     */
    fun createTeacher(): User {
        val user = create()
        user.role = RoleType.ROLE_TEACHER
        return user
    }

    /**
     * Create a mock Student
     */
    fun createStudent(): User {
        val user = create()
        user.role = RoleType.ROLE_STUDENT
        return user
    }

}