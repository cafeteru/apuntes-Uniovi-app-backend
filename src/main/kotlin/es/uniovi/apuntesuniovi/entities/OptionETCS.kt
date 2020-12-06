package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

/**
 * Represents option ETCS
 */
@Entity
class OptionETCS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    lateinit var configurationECTS: ConfigurationECTS

    @ManyToMany
    var subjectTypes: Set<SubjectType> = HashSet()

    var etcs: Int = 0
}