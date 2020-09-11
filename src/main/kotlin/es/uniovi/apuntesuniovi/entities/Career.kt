package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0

    lateinit var name: String
    var minECTS: Int = 0

    @ManyToOne
    lateinit var universityCenter: UniversityCenter

    @ManyToOne
    lateinit var configurationECTS: ConfigurationECTS

    @OneToMany(mappedBy = "career", cascade = [(CascadeType.ALL)])
    val courses: Set<Course> = HashSet()
}