package es.uniovi.apuntesuniovi.models

import java.time.LocalDate
import javax.persistence.*

/**
 * Represents registries of teachSubject
 */
@Entity
class TeachSubjectRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    lateinit var teachSubject: TeachSubject

    lateinit var initDay: LocalDate
    var finishDay: LocalDate? = null
}