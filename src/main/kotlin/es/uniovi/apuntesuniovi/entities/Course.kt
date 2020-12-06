package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

/**
 * Represents courses
 */
@Entity
class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var position: Int = 0

    @ManyToOne
    lateinit var career: Career

    @OneToMany(mappedBy = "course", cascade = [(CascadeType.ALL)])
    val semesters: Set<Semester> = HashSet()
}