package es.uniovi.apuntesuniovi.repositories.builders

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.models.Address
import es.uniovi.apuntesuniovi.models.QAddress

/**
 * Class to create conditions to filter users
 */
class AddressBuilder {

  /**
   * Create conditions to filter users
   */
  fun createBuilder(address: Address?): BooleanBuilder {
    val builder = BooleanBuilder()
    val qAddress = QAddress.address
    address?.let {
      createStreetFilter(it, builder, qAddress)
      createCityFilter(it, builder, qAddress)
      createPostalCodeFilter(it, builder, qAddress)
      createCountryFilter(it, builder, qAddress)
    }
    return builder
  }

  private fun createStreetFilter(
    address: Address,
    builder: BooleanBuilder,
    qAddress: QAddress
  ) {
    if (address.street != null) {
      builder.and(
        qAddress.street.like(
          Expressions.asString("%").concat(address.street).concat("%")
        )
      )
    }
  }

  private fun createCityFilter(
    address: Address,
    builder: BooleanBuilder,
    qAddress: QAddress
  ) {
    if (address.city != null) {
      builder.and(
        qAddress.city.like(
          Expressions.asString("%").concat(address.city).concat("%")
        )
      )
    }
  }

  private fun createPostalCodeFilter(
    address: Address,
    builder: BooleanBuilder,
    qAddress: QAddress
  ) {
    if (address.postalCode != null) {
      builder.and(
        qAddress.postalCode.like(
          Expressions.asString("%").concat(address.postalCode).concat("%")
        )
      )
    }
  }

  private fun createCountryFilter(
    address: Address,
    builder: BooleanBuilder,
    qAddress: QAddress
  ) {
    if (address.country != null) {
      builder.and(
        qAddress.country.like(
          Expressions.asString("%").concat(address.country).concat("%")
        )
      )
    }
  }
}