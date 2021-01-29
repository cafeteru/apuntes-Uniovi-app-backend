package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.SemesterLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.SemesterMessages
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMinValue
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Min

/**
 * Represents semesters
 */
@Entity
class Semester {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null

  @Min(SemesterLimits.POSITION_MIN.toLong())
  var position: Int? = null
    set(value) {
      value?.let {
        if (!ValidatorMinValue(it, SemesterLimits.POSITION_MIN).isValid()) {
          throw IllegalArgumentException(SemesterMessages.LIMIT_POSITION_MIN)
        }
      }
      field = value
    }

  @ManyToOne
  var course: Course? = null

  @OneToMany(mappedBy = "semester", cascade = [(CascadeType.ALL)])
  val subjects: Set<Subject> = HashSet()
}