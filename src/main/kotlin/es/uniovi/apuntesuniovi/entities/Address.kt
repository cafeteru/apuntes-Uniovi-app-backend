package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    lateinit var street: String
    lateinit var city: String
    lateinit var postalCode: String
    lateinit var country: String
}