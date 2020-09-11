package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

@Entity
class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0

    var position: Int = 0

    @ManyToOne
    lateinit var career: Career

    @OneToMany(mappedBy = "course", cascade = [(CascadeType.ALL)])
    val semesters: Set<Semester> = HashSet()
}