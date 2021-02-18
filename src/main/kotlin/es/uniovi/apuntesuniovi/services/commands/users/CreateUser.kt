package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.BaseCreateService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.util.Assert

/**
 * Create a user in service layer
 */
class CreateUser(
  private val userRepository: UserRepository,
  private val addressRepository: AddressRepository,
  private val user: User
) : BaseCreateService<User>(userRepository, user) {

  override fun checkData() {
    logService.info("checkData() - start")
    checkUniqueUsername()
    checkUniqueNumberIdentification()
    user.password = BCryptPasswordEncoder().encode(user.password)
    user.address?.let {
      user.address = addressRepository.save(it)
    }
    logService.info("checkData() - start")
  }

  private fun checkUniqueUsername() {
    logService.info("checkUniqueUsername() - start")
    Assert.isTrue(!user.username.isNullOrEmpty() && !user.password.isNullOrBlank(), UserMessages.INVALID_DATA_USER)
    val optional = user.username?.let { userRepository.findByUsername(it) }
    Assert.isTrue(optional == null || !optional.isPresent, UserMessages.ALREADY_REGISTERED_USERNAME)
    logService.info("checkUniqueUsername() - end")
  }

  private fun checkUniqueNumberIdentification() {
    logService.info("checkUniqueNumberIdentification() - start")
    val optional = user.numberIdentification?.let { userRepository.findByNumberIdentification(it) }
    Assert.isTrue(optional == null || !optional.isPresent, UserMessages.ALREADY_REGISTERED_NUMBER_IDENTIFICATION)
    logService.info("checkUniqueNumberIdentification() - end")
  }
}