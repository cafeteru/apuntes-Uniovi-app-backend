package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToMany
    val lessons: Set<Lesson> = HashSet()

    @ManyToMany(mappedBy = "tests", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    val questions: Set<Question> = HashSet()
}