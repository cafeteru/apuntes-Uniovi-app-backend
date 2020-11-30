package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

@Entity
class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    lateinit var name: String

    @ManyToOne
    lateinit var semester: Semester

    @ManyToOne
    lateinit var subjectType: SubjectType

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val teachSubjects: Set<TeachSubject> = HashSet()

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val lessons: Set<Lesson> = HashSet()
}