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
    val list = userRepository.findAll(filter(), pageable)
    logService.info("execute() - end")
    return list
  }

  private fun filter(): BooleanBuilder {
    val builder = BooleanBuilder()
    val qUser = QUser.user
    userDto?.let {
      if (!it.name.isNullOrEmpty()) {
        builder.and(
          qUser.name.like(
            Expressions.asString("%").concat(it.name).concat("%")
          )
        )
      }
      if (!it.surname.isNullOrEmpty()) {
        builder.and(
          qUser.surname.like(
            Expressions.asString("%").concat(it.surname).concat("%")
          )
        )
      }
      if (!it.email.isNullOrEmpty()) {
        builder.and(
          qUser.email.like(
            Expressions.asString("%").concat(it.email).concat("%")
          )
        )
      }
      if (!it.phone.isNullOrEmpty()) {
        builder.and(
          qUser.phone.like(
            Expressions.asString("%").concat(it.phone).concat("%")
          )
        )
      }
      builder.and(qUser.active.eq(it.active))
      if (it.birthDate != null) {
        builder.and(qUser.birthDate.eq(it.birthDate))
      }
      if (!it.username.isNullOrEmpty()) {
        builder.and(
          qUser.username.like(
            Expressions.asString("%").concat(it.username).concat("%")
          )
        )
      }
      if (!it.role.isNullOrEmpty()) {
        builder.and(
          qUser.role.eq(RoleType.valueOf(it.role!!))
        )
      }
      if (!it.identificationType.isNullOrEmpty()) {
        builder.and(
          qUser.identificationType.eq(IdentificationType.valueOf(it.identificationType!!))
        )
      }
      if (!it.numberIdentification.isNullOrEmpty()) {
        builder.and(
          qUser.numberIdentification.like(
            Expressions.asString("%").concat(it.numberIdentification).concat("%")
          )
        )
      }
      it.address?.let { address ->
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
    return builder
  }
}