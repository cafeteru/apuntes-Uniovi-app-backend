package es.uniovi.apuntesuniovi.entities

import org.joda.time.LocalDate
import javax.persistence.*

@Entity
class TeachSubjectRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @ManyToOne
    private var teachSubject: TeachSubject? = null

    private var initDay: LocalDate? = null
    private var finishDay: LocalDate? = null
}