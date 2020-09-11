package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class LearnSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0

    @ManyToOne
    lateinit var student: User

    @ManyToOne
    lateinit var subject: Subject

    var pass: Boolean = false
}