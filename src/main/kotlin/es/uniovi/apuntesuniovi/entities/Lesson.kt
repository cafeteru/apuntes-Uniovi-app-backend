package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

/**
 * Represents lessons
 */
@Entity
class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    lateinit var subject: Subject

    @ManyToMany(mappedBy = "lessons", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    val tests: Set<Test> = HashSet()
}