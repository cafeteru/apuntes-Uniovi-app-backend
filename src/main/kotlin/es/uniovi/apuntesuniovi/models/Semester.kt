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

    // TODO ADD LIMIT
    var position: Int? = null

    @ManyToOne
    lateinit var course: Course

    @OneToMany(mappedBy = "semester", cascade = [(CascadeType.ALL)])
    val subjects: Set<Subject> = HashSet()
}