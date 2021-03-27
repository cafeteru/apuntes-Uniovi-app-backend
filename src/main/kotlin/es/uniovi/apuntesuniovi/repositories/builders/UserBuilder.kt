package es.uniovi.apuntesuniovi.repositories.builders

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.models.QUser
import es.uniovi.apuntesuniovi.models.types.IdentificationType
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.dtos.entities.UserDto

/**
 * Class to create conditions to filter addresses
 */
class UserBuilder {

  /**
   * Create conditions to filter addresses
   */
  fun createBuilder(userDto: UserDto?): BooleanBuilder {
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
      builder.and(AddressBuilder().createBuilder(it.address))
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
}