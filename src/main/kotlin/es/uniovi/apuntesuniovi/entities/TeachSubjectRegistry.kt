package es.uniovi.apuntesuniovi.entities

import org.joda.time.LocalDate
import javax.persistence.*

@Entity
class TeachSubjectRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var teachSubject: TeachSubject? = null

    var initDay: LocalDate? = null
    var finishDay: LocalDate? = null
}