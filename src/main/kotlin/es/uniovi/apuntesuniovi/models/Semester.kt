package es.uniovi.apuntesuniovi.models

import java.util.HashSet
import javax.persistence.*

/**
 * Represents semesters
 */
@Entity
class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var position = 0

    @ManyToOne
    lateinit var course: Course

    @OneToMany(mappedBy = "semester", cascade = [(CascadeType.ALL)])
    val subjects: Set<Subject> = HashSet()
}