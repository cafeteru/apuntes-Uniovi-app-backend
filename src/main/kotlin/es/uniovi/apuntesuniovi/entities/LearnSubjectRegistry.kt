package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class LearnSubjectRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne
    lateinit var learnSubject: LearnSubject

    @ManyToOne
    lateinit var academicCourse: AcademicCourse

    var mark: Float = 0f
    var number: Int = 0
}