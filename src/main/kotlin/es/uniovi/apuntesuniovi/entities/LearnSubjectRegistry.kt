package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

/**
 * Represents relationship betwenn learnSubject and AcademicCourse
 */
@Entity
class LearnSubjectRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    lateinit var learnSubject: LearnSubject

    @ManyToOne
    lateinit var academicCourse: AcademicCourse

    var mark: Float = 0f
    var number: Int = 0
}