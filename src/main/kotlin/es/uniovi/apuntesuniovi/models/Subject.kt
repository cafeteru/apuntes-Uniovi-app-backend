package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.SubjectLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.models.types.SubjectType
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxLength
import java.util.*
import javax.persistence.*

/**
 * Represents subjects
 */
@Entity
class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(length = SubjectLimits.NAME)
    var name: String = ""
        set(value) {
            if (ValidatorMaxLength(value, SubjectLimits.NAME).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(SubjectMessages.LIMIT_NAME)
            }
        }

    @Enumerated(EnumType.STRING)
    var subjectType: SubjectType? = null

    @ManyToOne
    var semester: Semester? = null

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val teachSubjects: Set<TeachSubject> = HashSet()

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val lessons: Set<Lesson> = HashSet()

    /**
     * Set subjectType according to a text
     *
     * @param subjectType Text
     * @throws IllegalArgumentException Invalid text
     */
    fun setSubjectType(subjectType: String?) {
        if (subjectType != null) {
            try {
                this.subjectType = SubjectType.valueOf(
                    subjectType.toUpperCase()
                )
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException(SubjectMessages.INVALID_SUBJECT_TYPE)
            }
        } else {
            this.subjectType = null
        }
    }
}