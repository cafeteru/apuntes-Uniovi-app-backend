package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

@Entity
class TeachSubjectRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne
    lateinit var teachSubject: TeachSubject

    lateinit var initDay: Date
    lateinit var finishDay: Date
}