package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class LearnSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var student: User? = null

    @ManyToOne
    var subject: Subject? = null

    var pass: Boolean = false
}