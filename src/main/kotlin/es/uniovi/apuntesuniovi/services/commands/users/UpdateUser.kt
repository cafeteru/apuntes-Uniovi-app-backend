package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.util.Assert

/**
 * Update a user in service layer
 */
class UpdateUser(
  private val userRepository: UserRepository,
  private val addressRepository: AddressRepository,
  private val id: Long,
  private val user: User
) : AbstractCommand<User>() {

  override fun execute(): User {
    logService.info("execute() - start")
    val optional = userRepository.findById(id)
    if (optional.isPresent) {
      if (user.password != null) {
        user.password = BCryptPasswordEncoder().encode(user.password)
      } else {
        user.password = optional.get().password
      }
      checkUniqueUsername()
      checkUniqueNumberIdentification()
      user.id = id
      user.address?.let {
        user.address = addressRepository.save(it)
      }
      val result = userRepository.save(user)
      logService.info("execute() - end")
      return result
    }
    throw IllegalArgumentException(UserMessages.NOT_FOUND)
  }

  private fun checkUniqueUsername() {
    logService.info("checkUniqueUsername() - start")
    Assert.isTrue(!user.username.isNullOrEmpty() && !user.password.isNullOrBlank(), UserMessages.INVALID_DATA_USER)
    val optional = user.username?.let { userRepository.findByUsername(it) }
    val expression = optional == null || !optional.isPresent || (optional.isPresent && optional.get().id == id)
    Assert.isTrue(expression, UserMessages.ALREADY_REGISTERED_USERNAME)
    logService.info("checkUniqueUsername() - end")
  }

  private fun checkUniqueNumberIdentification() {
    logService.info("checkUniqueNumberIdentification() - start")
    val optional = user.numberIdentification?.let { userRepository.findByNumberIdentification(it) }
    val expression = optional == null || !optional.isPresent || (optional.isPresent && optional.get().id == id)
    Assert.isTrue(expression, UserMessages.ALREADY_REGISTERED_NUMBER_IDENTIFICATION)
    logService.info("checkUniqueNumberIdentification() - end")
  }
}