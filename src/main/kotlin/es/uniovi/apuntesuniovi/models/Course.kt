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

    var position: Int? = null

    @ManyToOne
    var career: Career? = null

    @OneToMany(mappedBy = "course", cascade = [(CascadeType.ALL)])
    val semesters: Set<Semester> = HashSet()
}