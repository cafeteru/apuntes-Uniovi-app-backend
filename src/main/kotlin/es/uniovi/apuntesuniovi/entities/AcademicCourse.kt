package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

@Entity
class AcademicCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(mappedBy = "student", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()

    lateinit var initDay: Date
    lateinit var finishDay: Date
}