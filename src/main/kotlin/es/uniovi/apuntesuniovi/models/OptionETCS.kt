package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.OptionETCSLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.OptionETCSMessages
import es.uniovi.apuntesuniovi.models.types.SubjectType
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxValue
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMinValue
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

/**
 * Represents option ETCS
 */
@Entity
class OptionETCS {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null

  @Min(OptionETCSLimits.ECTS_MIN.toLong())
  @Max(OptionETCSLimits.ECTS_MAX.toLong())
  var etcs: Int = OptionETCSLimits.ECTS_MIN
    set(value) {
      if (!ValidatorMinValue(value, OptionETCSLimits.ECTS_MIN).isValid()) {
        throw IllegalArgumentException(OptionETCSMessages.LIMIT_ETCS_MIN)
      }
      if (!ValidatorMaxValue(value, OptionETCSLimits.ECTS_MAX).isValid()) {
        throw IllegalArgumentException(OptionETCSMessages.LIMIT_ETCS_MAX)
      }
      field = value
    }

  @Enumerated(EnumType.STRING)
  var subjectType: SubjectType? = null

  @ManyToOne
  var career: Career? = null

  /**
   * Set subjectType according to a text
   *
   * @param subjectType Text
   * @throws IllegalArgumentException Invalid text
   */
  fun setSubjectType(subjectType: String?) {
    if (subjectType != null) {
      try {
        this.subjectType = SubjectType.valueOf(subjectType.toUpperCase())
      } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException(OptionETCSMessages.INVALID_SUBJECT_TYPE)
      }
    } else {
      this.subjectType = null
    }
  }
}