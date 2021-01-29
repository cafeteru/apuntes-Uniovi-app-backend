package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.users.CreateUserService
import es.uniovi.apuntesuniovi.services.commands.users.FindAllUsersService
import es.uniovi.apuntesuniovi.services.commands.users.FindUserByIdService
import es.uniovi.apuntesuniovi.services.commands.users.FindUserByUsernameService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.UserAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
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
  private val userAssembler: UserAssembler
) {
  private val logService = LogService(this.javaClass)

  /**
   * Create a new element
   *
   * @param dto Element to create
   */
  fun create(dto: UserDto): UserDto {
    logService.info("create(dto: UserDto) - start")
    val entity = userAssembler.dtoToEntity(dto)
    val result = CreateUserService(userRepository, addressRepository, entity).execute()
    logService.info("create(dto: UserDto) - end")
    return userAssembler.entityToDto(deleteImgPassword(result))
  }

  /**
   * Returns all elements
   */
  fun findAll(pageable: Pageable): Page<UserDto> {
    logService.info("findAll() - start")
    val result = FindAllUsersService(userRepository, pageable).execute()
    logService.info("findAll() - end")
    return result.map { entity -> userAssembler.entityToDto(deleteImgPassword(entity)) }
  }

  fun findById(id: Long): UserDto {
    logService.info("findById() - start")
    val result = FindUserByIdService(userRepository, id).execute()
    logService.info("findById() - end")
    return userAssembler.entityToDto(deleteImgPassword(result))
  }

  /**
   * Returns the user whose username matches
   *
   * @param username User identifier
   */
  fun findByUsername(username: String): UserDto {
    logService.info("findByUsername(username: ${username}) - start")
    val result = FindUserByUsernameService(userRepository, username).execute()
    logService.info("findByUsername(username: ${username}) - end")
    return userAssembler.entityToDto(result)
  }

  private fun deleteImgPassword(user: User): User {
    user.img = null
    user.password = null
    return user
  }
}