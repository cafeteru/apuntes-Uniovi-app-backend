package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @ManyToMany
    private val lessons: Set<Lesson> = HashSet()

    @ManyToMany(mappedBy = "tests", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    private val questions: Set<Question> = HashSet()
}