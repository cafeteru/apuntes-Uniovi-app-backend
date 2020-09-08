package es.uniovi.apuntesuniovi.entities

import org.joda.time.LocalDate
import javax.persistence.*

@Entity
class AcademicCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0

    @OneToMany(mappedBy = "student", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()

    lateinit var initDay: LocalDate
    lateinit var finishDay: LocalDate
}