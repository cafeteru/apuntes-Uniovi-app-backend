package es.uniovi.apuntesuniovi.services.commands.users

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.models.QUser
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.models.types.IdentificationType
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Return all users in service layer
 */
class FindAllUsers(
  private val userRepository: UserRepository,
  private val userDto: UserDto?,
  private val pageable: Pageable
) : AbstractCommand<Page<User>>() {

  override fun execute(): Page<User> {
    logService.info("execute() - start")
    val list = userRepository.findAll(createFilters(), pageable)
    logService.info("execute() - end")
    return list
  }

  private fun createFilters(): BooleanBuilder {
    val builder = BooleanBuilder()
    val qUser = QUser.user
    userDto?.let {
      createNameFilter(it, builder, qUser)
      createSurnameFilter(it, builder, qUser)
      createEmailFilter(it, builder, qUser)
      createPhoneFilter(it, builder, qUser)
      builder.and(qUser.active.eq(it.active))
      if (it.birthDate != null) {
        builder.and(qUser.birthDate.eq(it.birthDate))
      }
      createUsernameFilter(it, builder, qUser)
      createRoleFilter(it, builder, qUser)
      createIdentificationTypeFilter(it, builder, qUser)
      createNumberIdentificationFilter(it, builder, qUser)
      createAddressFilter(it, builder, qUser)
    }
    return builder
  }

  private fun createNameFilter(
    userDto: UserDto,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!userDto.name.isNullOrEmpty()) {
      builder.and(
        qUser.name.like(
          Expressions.asString("%").concat(userDto.name).concat("%")
        )
      )
    }
  }

  private fun createSurnameFilter(
    userDto: UserDto,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!userDto.surname.isNullOrEmpty()) {
      builder.and(
        qUser.surname.like(
          Expressions.asString("%").concat(userDto.surname).concat("%")
        )
      )
    }
  }

  private fun createEmailFilter(
    userDto: UserDto,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!userDto.email.isNullOrEmpty()) {
      builder.and(
        qUser.email.like(
          Expressions.asString("%").concat(userDto.email).concat("%")
        )
      )
    }
  }

  private fun createPhoneFilter(
    userDto: UserDto,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!userDto.phone.isNullOrEmpty()) {
      builder.and(
        qUser.phone.like(
          Expressions.asString("%").concat(userDto.phone).concat("%")
        )
      )
    }
  }

  private fun createUsernameFilter(
    userDto: UserDto,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!userDto.username.isNullOrEmpty()) {
      builder.and(
        qUser.username.like(
          Expressions.asString("%").concat(userDto.username).concat("%")
        )
      )
    }
  }

  private fun createRoleFilter(
    userDto: UserDto,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!userDto.role.isNullOrEmpty()) {
      builder.and(
        qUser.role.eq(RoleType.valueOf(userDto.role!!))
      )
    }
  }

  private fun createIdentificationTypeFilter(
    userDto: UserDto,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!userDto.identificationType.isNullOrEmpty()) {
      builder.and(
        qUser.identificationType.eq(IdentificationType.valueOf(userDto.identificationType!!))
      )
    }
  }

  private fun createNumberIdentificationFilter(
    userDto: UserDto,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!userDto.numberIdentification.isNullOrEmpty()) {
      builder.and(
        qUser.numberIdentification.like(
          Expressions.asString("%").concat(userDto.numberIdentification).concat("%")
        )
      )
    }
  }

  private fun createAddressFilter(
    userDto: UserDto,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    userDto.address?.let { address ->
      if (!address.street.isNullOrEmpty()) {
        builder.and(
          qUser.address.street.like(
            Expressions.asString("%").concat(address.street).concat("%")
          )
        )
      }
      if (!address.city.isNullOrEmpty()) {
        builder.and(
          qUser.address.city.like(
            Expressions.asString("%").concat(address.city).concat("%")
          )
        )
      }
      if (!address.postalCode.isNullOrEmpty()) {
        builder.and(
          qUser.address.postalCode.like(
            Expressions.asString("%").concat(address.postalCode).concat("%")
          )
        )
      }
      if (!address.country.isNullOrEmpty()) {
        builder.and(
          qUser.address.country.like(
            Expressions.asString("%").concat(address.country).concat("%")
          )
        )
      }
    }
  }
}