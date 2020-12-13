package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.CourseLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.CourseMessages
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMinValue
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Min

/**
 * Represents courses
 */
@Entity
class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Min(CourseLimits.POSITION_MIN.toLong())
    var position: Int? = null
        set(value) {
            value?.let {
                if (!ValidatorMinValue(it, CourseLimits.POSITION_MIN).isValid()) {
                    throw IllegalArgumentException(CourseMessages.LIMIT_POSITION_MIN)
                }
            }
            field = value
        }

    @ManyToOne
    var career: Career? = null

    @OneToMany(mappedBy = "course", cascade = [(CascadeType.ALL)])
    val semesters: Set<Semester> = HashSet()
}