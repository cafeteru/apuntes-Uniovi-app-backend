package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

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