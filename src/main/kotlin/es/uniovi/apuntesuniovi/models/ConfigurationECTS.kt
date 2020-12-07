package es.uniovi.apuntesuniovi.models

import java.util.*
import javax.persistence.*

/**
 * Represents configurations ECTS
 */
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