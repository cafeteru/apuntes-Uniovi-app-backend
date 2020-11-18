package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

@Entity
class AcademicCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0

    @OneToMany(mappedBy = "student", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()

    lateinit var initDay: Date
    lateinit var finishDay: Date
}