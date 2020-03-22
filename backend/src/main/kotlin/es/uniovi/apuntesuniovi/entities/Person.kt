package es.uniovi.apuntesuniovi.entities

import java.time.LocalDate
import javax.persistence.*


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
    private var name: String = ""
    private var surname: String = ""
    private var email: String = ""
    private var phone: String = ""
    private var active: Boolean = true
    private var img: String = ""
    private var birthDate: LocalDate? = null
}