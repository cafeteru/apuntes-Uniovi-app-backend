package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.users.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

/**
 * Service to manage users
 */
@Service
class UserService @Autowired constructor(
  private val userRepository: UserRepository,
  private val addressRepository: AddressRepository,
) {
  private val logService = LogService(this.javaClass)

  /**
   * Create a new user
   *
   * @param user User to create
   */
  fun create(user: User): User {
    logService.info("create(dto: UserDto) - start")
    val result = CreateUser(userRepository, addressRepository, user).execute()
    logService.info("create(dto: UserDto) - end")
    return result
  }

  /**
   * Update a new user
   *
   * @param id User´s id
   * @param user User to update
   */
  fun update(id: Long, user: User): User {
    logService.info("update(id: Long, dto: UserDto) - start")
    val result = UpdateUser(userRepository, addressRepository, id, user).execute()
    logService.info("update(id: Long, dto: UserDto) - end")
    return result
  }

  /**
   * Returns all users
   *
   * @param pageable Pageable
   *
   * @return Page
   */
  fun findAll(pageable: Pageable, userDto: User?): Page<User> {
    logService.info("findAll() - start")
    val page = FindAllUsers(userRepository, userDto, pageable).execute()
    page.content.forEach { user ->
      run {
        user.img = null
        user.password = null
      }
    }
    logService.info("findAll() - end")
    return page
  }

  /**
   * Returns the user whose id matches
   *
   * @param id User id
   */
  fun findById(id: Long): User {
    logService.info("findById() - start")
    val user = FindUserById(userRepository, id).execute()
    logService.info("findById() - end")
    return user
  }

  /**
   * Returns the user whose username matches
   *
   * @param username User identifier
   */
  fun findByUsername(username: String): User {
    logService.info("findByUsername(username: ${username}) - start")
    val user = FindUserByUsername(userRepository, username).execute()
    user.password = null
    logService.info("findByUsername(username: ${username}) - end")
    return user
  }

  /**
   * Change a user's language
   *
   * @param username User's username sending the request
   * @param language Selected language
   */
  fun changeLanguage(username: String, language: String): Boolean {
    logService.info("changeLanguage(username: $username, language: $language) - start")
    val result = ChangeLanguageUser(userRepository, username, language).execute()
    logService.info("changeLanguage(username: $username, language:  $language) - end")
    return result
  }

  /**
   * Change the value active of a user
   *
   * @param id User´s id
   * @param active New value to user´s active
   */
  fun disable(id: Long, active: Boolean): User {
    logService.info("disable(id: $id, active: $active) - start")
    val user = DisableUser(userRepository, id, active).execute()
    logService.info("disable(id: $id, active:  $active) - end")
    return user
  }

  /**
   * Delete a user
   *
   * @param id User´s id
   */
  fun delete(id: Long): Boolean {
    logService.info("delete(id: $id) - start")
    val result = DeleteUser(userRepository, id).execute()
    logService.info("delete(id: $id) - end")
    return result
  }
}