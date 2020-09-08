package es.uniovi.apuntesuniovi.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0
}