package es.uniovi.apuntesuniovi.models

import java.util.*
import javax.persistence.*

/**
 * Represents questions
 */
@Entity
class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToMany
    val tests: Set<Test> = HashSet()
}