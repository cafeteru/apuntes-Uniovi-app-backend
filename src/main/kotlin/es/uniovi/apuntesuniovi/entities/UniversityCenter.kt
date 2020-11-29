package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

@Entity
class UniversityCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    lateinit var name: String

    @OneToMany(mappedBy = "universityCenter", cascade = [(CascadeType.ALL)])
    val careers: Set<Career> = HashSet()

    @OneToOne
    lateinit var address: Address
}