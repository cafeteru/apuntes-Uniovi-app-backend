package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class OptionETCS {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0

    @ManyToOne
    lateinit var configurationECTS: ConfigurationECTS

    @ManyToMany
    var subjectTypes: Set<SubjectType> = HashSet()

    var etcs: Int = 0
}