package es.uniovi.apuntesuniovi.models

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

    // TODO ADD LIMIT
    var position: Int? = null

    @ManyToOne
    lateinit var career: Career

    @OneToMany(mappedBy = "course", cascade = [(CascadeType.ALL)])
    val semesters: Set<Semester> = HashSet()
}