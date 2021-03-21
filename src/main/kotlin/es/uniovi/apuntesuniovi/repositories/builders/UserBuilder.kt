package es.uniovi.apuntesuniovi.repositories.builders

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.models.QUser
import es.uniovi.apuntesuniovi.models.User

/**
 * Class to create conditions to filter addresses
 */
class UserBuilder {

  /**
   * Create conditions to filter addresses
   */
  fun createBuilder(user: User?): BooleanBuilder {
    val builder = BooleanBuilder()
    val qUser = QUser.user
    user?.let {
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
    user: User,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!user.name.isNullOrEmpty()) {
      builder.and(
        qUser.name.like(
          Expressions.asString("%").concat(user.name).concat("%")
        )
      )
    }
  }

  private fun createSurnameFilter(
    user: User,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!user.surname.isNullOrEmpty()) {
      builder.and(
        qUser.surname.like(
          Expressions.asString("%").concat(user.surname).concat("%")
        )
      )
    }
  }

  private fun createEmailFilter(
    user: User,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!user.email.isNullOrEmpty()) {
      builder.and(
        qUser.email.like(
          Expressions.asString("%").concat(user.email).concat("%")
        )
      )
    }
  }

  private fun createPhoneFilter(
    user: User,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!user.phone.isNullOrEmpty()) {
      builder.and(
        qUser.phone.like(
          Expressions.asString("%").concat(user.phone).concat("%")
        )
      )
    }
  }

  private fun createUsernameFilter(
    user: User,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!user.username.isNullOrEmpty()) {
      builder.and(
        qUser.username.like(
          Expressions.asString("%").concat(user.username).concat("%")
        )
      )
    }
  }

  private fun createRoleFilter(
    user: User,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (user.role != null) {
      builder.and(qUser.role.eq(user.role))
    }
  }

  private fun createIdentificationTypeFilter(
    user: User,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (user.identificationType != null) {
      builder.and(
        qUser.identificationType.eq(user.identificationType)
      )
    }
  }

  private fun createNumberIdentificationFilter(
    user: User,
    builder: BooleanBuilder,
    qUser: QUser
  ) {
    if (!user.numberIdentification.isNullOrEmpty()) {
      builder.and(
        qUser.numberIdentification.like(
          Expressions.asString("%").concat(user.numberIdentification).concat("%")
        )
      )
    }
  }
}