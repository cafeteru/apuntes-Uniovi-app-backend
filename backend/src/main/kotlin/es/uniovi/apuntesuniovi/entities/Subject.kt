package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    private val lessons: Set<Lesson> = HashSet()
}