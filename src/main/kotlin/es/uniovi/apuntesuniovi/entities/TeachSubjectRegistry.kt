package es.uniovi.apuntesuniovi.entities

import java.time.LocalDate
import javax.persistence.*

@Entity
class TeachSubjectRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne
    lateinit var teachSubject: TeachSubject

    lateinit var initDay: LocalDate
    lateinit var finishDay: LocalDate
}