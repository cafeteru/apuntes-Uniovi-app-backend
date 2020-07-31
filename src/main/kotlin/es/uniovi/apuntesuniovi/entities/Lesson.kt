package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var subject: Subject? = null

    @ManyToMany(mappedBy = "lessons", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    val tests: Set<Test> = HashSet()
}