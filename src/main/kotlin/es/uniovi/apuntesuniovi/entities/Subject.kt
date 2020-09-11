package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0

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