package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

/**
 * Represents careers
 */
@Entity
class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    lateinit var name: String
    var code: String? = null
    var yearImplantation: String? = null
    var typeTeaching: String? = null
    var ISCED: String? = null
    var ECTS: Int? = null
    var languages: String? = null

    @ManyToOne
    var center: Center? = null

    @ManyToOne
    var configurationECTS: ConfigurationECTS? = null

    @OneToMany(mappedBy = "career", cascade = [(CascadeType.ALL)])
    val courses: Set<Course> = HashSet()
}