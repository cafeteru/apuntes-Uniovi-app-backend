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
    var teachSubject: TeachSubject? = null

    lateinit var initDay: LocalDate
    var finishDay: LocalDate? = null
}