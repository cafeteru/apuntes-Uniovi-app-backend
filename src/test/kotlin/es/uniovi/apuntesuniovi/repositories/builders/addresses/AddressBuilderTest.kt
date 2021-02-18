package es.uniovi.apuntesuniovi.repositories.builders.addresses

import com.querydsl.core.types.dsl.Expressions
import es.uniovi.apuntesuniovi.mocks.entities.MockAddressCreator
import es.uniovi.apuntesuniovi.models.Address
import es.uniovi.apuntesuniovi.models.QAddress
import es.uniovi.apuntesuniovi.repositories.builders.AddressBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test class AddressBuilder
 */
class AddressBuilderTest {
  private lateinit var address: Address
  private lateinit var qAddress: QAddress

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initData() {
    address = MockAddressCreator().create()
    qAddress = QAddress.address
  }

  /**
   * Checks conditions with null address
   */
  @Test
  fun nullAddress() {
    val builder = AddressBuilder().createBuilder(null)
    Assertions.assertNull(builder.value)
  }

  /**
   * Checks conditions with null street
   */
  @Test
  fun nullStreet() {
    val expression = qAddress.street.like(
      Expressions.asString("%").concat(address.street).concat("%")
    )
    address.street = null
    val builder = AddressBuilder().createBuilder(address)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with null city
   */
  @Test
  fun nullCity() {
    val expression = qAddress.city.like(
      Expressions.asString("%").concat(address.city).concat("%")
    )
    address.city = null
    val builder = AddressBuilder().createBuilder(address)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with null postalCode
   */
  @Test
  fun nullPostalCode() {
    val expression = qAddress.postalCode.like(
      Expressions.asString("%").concat(address.postalCode).concat("%")
    )
    address.postalCode = null
    val builder = AddressBuilder().createBuilder(address)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }

  /**
   * Checks conditions with null country
   */
  @Test
  fun nullCountry() {
    val expression = qAddress.country.like(
      Expressions.asString("%").concat(address.country).concat("%")
    )
    address.country = null
    val builder = AddressBuilder().createBuilder(address)
    Assertions.assertFalse(builder.value.toString().contains(expression.toString()))
  }
}