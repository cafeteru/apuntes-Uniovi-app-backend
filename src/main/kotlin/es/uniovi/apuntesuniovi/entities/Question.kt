package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

@Entity
class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToMany
    val tests: Set<Test> = HashSet()
}