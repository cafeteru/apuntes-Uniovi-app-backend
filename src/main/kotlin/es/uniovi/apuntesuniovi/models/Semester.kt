package es.uniovi.apuntesuniovi.models

import java.util.*
import javax.persistence.*

/**
 * Represents semesters
 */
@Entity
class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var position: Int? = null

    @ManyToOne
    var course: Course? = null

    @OneToMany(mappedBy = "semester", cascade = [(CascadeType.ALL)])
    val subjects: Set<Subject> = HashSet()
}