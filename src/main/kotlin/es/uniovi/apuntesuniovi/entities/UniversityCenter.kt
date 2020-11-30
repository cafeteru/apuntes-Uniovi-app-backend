package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

@Entity
class UniversityCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String? = null

    @OneToMany(mappedBy = "universityCenter", cascade = [(CascadeType.ALL)])
    val careers: Set<Career> = HashSet()

    @OneToOne
    var address: Address? = null
}