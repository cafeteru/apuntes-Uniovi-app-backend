package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.Center

/**
 * Service to create mock data of the entity Center
 */
class MockCenterCreator : MockCreator<Center> {
  override fun create(): Center {
    val center = Center()
    center.id = 1
    center.name = "CenterTest"
    center.address = MockAddressCreator().create()
    return center
  }
}