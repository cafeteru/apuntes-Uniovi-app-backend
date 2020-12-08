package es.uniovi.apuntesuniovi.models

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

    // TODO ADD LIMIT AND ALL ENTITY
    var etcs: Int = 0

    @ManyToOne
    lateinit var configurationECTS: ConfigurationECTS

    @ManyToMany
    var subjectTypes: Set<SubjectType> = HashSet()
}