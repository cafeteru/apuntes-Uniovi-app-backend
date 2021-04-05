package es.uniovi.apuntesuniovi.repositories.builders

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.dtos.entities.AddressDto
import es.uniovi.apuntesuniovi.models.QAddress

/**
 * Class to create conditions to filter users
 */
class AddressBuilder : Builder<AddressDto> {

    override fun createBuilder(dto: AddressDto?): BooleanBuilder {
        val builder = BooleanBuilder()
        val qAddress = QAddress.address
        dto?.let {
            createStreetFilter(it, builder, qAddress)
            createCityFilter(it, builder, qAddress)
            createPostalCodeFilter(it, builder, qAddress)
            createCountryFilter(it, builder, qAddress)
        }
        return builder
    }

    private fun createStreetFilter(
        address: AddressDto,
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
        address: AddressDto,
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
        address: AddressDto,
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
        address: AddressDto,
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