package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToMany
    val tests: Set<Test> = HashSet()
}