package es.uniovi.apuntesuniovi.entities

import org.joda.time.LocalDate
import javax.persistence.*

@Entity
class AcademicCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(mappedBy = "student", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()

    lateinit var initDay: LocalDate
    lateinit var finishDay: LocalDate
}