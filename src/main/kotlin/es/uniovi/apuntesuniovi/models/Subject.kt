package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.CenterLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.CenterMessages
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

    @Column(length = CenterLimits.NAME)
    var name: String = ""
        set(value) {
            if (ValidatorMaxLength(value, CenterLimits.NAME).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(CenterMessages.LIMIT_NAME)
            }
        }

    @ManyToOne
    var semester: Semester? = null

    @ManyToOne
    var subjectType: SubjectType? = null

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val teachSubjects: Set<TeachSubject> = HashSet()

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val lessons: Set<Lesson> = HashSet()
}