package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @ManyToMany
    private val tests: Set<Test> = HashSet()
}