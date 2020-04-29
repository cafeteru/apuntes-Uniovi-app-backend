package es.uniovi.apuntesuniovi.entities

import org.joda.time.LocalDate
import javax.persistence.*

@Entity
class AcademicCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @OneToMany(mappedBy = "student", cascade = [(CascadeType.ALL)])
    private val learnSubject: Set<LearnSubject> = HashSet()

    private var initDay: LocalDate? = null
    private var finishDay: LocalDate? = null
}