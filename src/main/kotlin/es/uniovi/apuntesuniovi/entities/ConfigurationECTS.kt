package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

@Entity
class ConfigurationECTS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(mappedBy = "configurationECTS", cascade = [(CascadeType.ALL)])
    val careers: Set<Career> = HashSet()

    @OneToMany(mappedBy = "configurationECTS", cascade = [(CascadeType.ALL)])
    val optionsETCS: Set<OptionETCS> = HashSet()

}