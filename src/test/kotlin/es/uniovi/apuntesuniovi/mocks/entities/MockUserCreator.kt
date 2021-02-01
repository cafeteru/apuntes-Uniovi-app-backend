package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.models.types.IdentificationType
import es.uniovi.apuntesuniovi.models.types.RoleType
import java.time.LocalDate

/**
 * Service to create mock data of the entity User
 */
class MockUserCreator : MockCreator<User> {
  override fun create(): User {
    val user = User()
    user.id = 1
    user.name = "Test"
    user.surname = "Test"
    user.active = true
    user.birthDate = LocalDate.now()
    user.email = "test@test.es"
    user.identificationType = IdentificationType.NIE
    user.numberIdentification = "03203911B"
    user.username = "test"
    user.password = "testPassword"
    user.role = RoleType.ADMIN
    user.phone = "623548956"
    user.img = "img"
    user.address = MockAddressCreator().create()
    return user
  }

  /**
   * Create a mock Teacher
   */
  fun createTeacher(): User {
    val user = create()
    user.role = RoleType.TEACHER
    return user
  }

  /**
   * Create a mock Student
   */
  fun createStudent(): User {
    val user = create()
    user.role = RoleType.STUDENT
    return user
  }

}