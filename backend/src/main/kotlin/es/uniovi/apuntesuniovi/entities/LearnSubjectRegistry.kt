package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class LearnSubjectRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @ManyToOne
    private var learnSubject: LearnSubject? = null

    @ManyToOne
    private var academicCourse: AcademicCourse? = null

    private var mark: Float = 0f
    private var number: Int = 0
}