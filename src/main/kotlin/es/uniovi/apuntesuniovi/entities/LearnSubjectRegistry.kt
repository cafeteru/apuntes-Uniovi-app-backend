package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class LearnSubjectRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var learnSubject: LearnSubject? = null

    @ManyToOne
    var academicCourse: AcademicCourse? = null

    var mark: Float = 0f
    var number: Int = 0
}