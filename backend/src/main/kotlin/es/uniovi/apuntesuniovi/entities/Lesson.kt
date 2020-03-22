package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @ManyToOne
    private var subject: Subject? = null

    @ManyToMany(mappedBy = "lessons", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    private val tests: Set<Test> = HashSet()
}