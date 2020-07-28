package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class LearnSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @ManyToOne
    private var student: User? = null

    @ManyToOne
    private var subject: Subject? = null

    private var pass: Boolean = false
}