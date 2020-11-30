package es.uniovi.apuntesuniovi.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var street: String = ""
    var city: String = ""
    var postalCode: String = ""
    var country: String = ""
}