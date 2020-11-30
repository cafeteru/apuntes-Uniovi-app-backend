package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

@Entity
class Career {
    @Id
    var id: Long? = null

    lateinit var name: String
    lateinit var code: String
    lateinit var yearImplantation: String
    lateinit var typeTeaching: String
    lateinit var ISCED: String
    var ECTS: Int = 0
    lateinit var languages: String

    @ManyToOne
    lateinit var universityCenter: UniversityCenter

    @ManyToOne
    lateinit var configurationECTS: ConfigurationECTS

    @OneToMany(mappedBy = "career", cascade = [(CascadeType.ALL)])
    val courses: Set<Course> = HashSet()
}