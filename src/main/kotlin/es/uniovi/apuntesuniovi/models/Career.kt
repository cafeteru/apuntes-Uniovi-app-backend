package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.CareerLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.CareerMessages
import es.uniovi.apuntesuniovi.models.types.LanguageType
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxLength
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxValue
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMinValue
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min


/**
 * Represents careers
 */
@Entity
class Career {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null

  @Column(length = CareerLimits.NAME)
  var name: String = ""
    set(value) {
      if (ValidatorMaxLength(value, CareerLimits.NAME).isValid()) {
        field = value
      } else {
        throw IllegalArgumentException(CareerMessages.LIMIT_NAME)
      }
    }

  @Column(length = CareerLimits.CODE)
  var code: String? = null
    set(value) {
      if (ValidatorMaxLength(value, CareerLimits.CODE).isValid()) {
        field = value
      } else {
        throw IllegalArgumentException(CareerMessages.LIMIT_CODE)
      }
    }

  @Min(CareerLimits.YEAR_IMPLANTATION.toLong())
  var yearImplantation: Int? = null
    set(value) {
      value?.let {
        if (!ValidatorMinValue(it, CareerLimits.YEAR_IMPLANTATION).isValid()) {
          throw IllegalArgumentException(CareerMessages.LIMIT_YEAR_IMPLANTATION)
        }
      }
      field = value
    }

  @Min(CareerLimits.ECTS_MIN.toLong())
  @Max(CareerLimits.ECTS_MAX.toLong())
  var etcs: Int? = null
    set(value) {
      value?.let {
        if (!ValidatorMinValue(it, CareerLimits.ECTS_MIN).isValid()) {
          throw IllegalArgumentException(CareerMessages.LIMIT_ETCS_MIN)
        }
        if (!ValidatorMaxValue(it, CareerLimits.ECTS_MAX).isValid()) {
          throw IllegalArgumentException(CareerMessages.LIMIT_ETCS_MAX)
        }
      }
      field = value
    }

  @ElementCollection
  @Enumerated(EnumType.STRING)
  var languages: MutableSet<LanguageType> = HashSet()

  @ManyToOne
  var center: Center? = null

  @OneToMany(mappedBy = "career", cascade = [(CascadeType.ALL)])
  var optionETCS: Set<OptionETCS> = HashSet()

  @OneToMany(mappedBy = "career", cascade = [(CascadeType.ALL)])
  val courses: Set<Course> = HashSet()

  /**
   * Add language according to a text
   *
   * @param language Text
   * @throws IllegalArgumentException Invalid text
   */
  fun addLanguage(language: String?) {
    if (language != null) {
      try {
        val value = LanguageType.valueOf(language.toUpperCase())
        languages.add(value)
      } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException(CareerMessages.INVALID_LANGUAGE)
      }
    } else {
      throw IllegalArgumentException(CareerMessages.INVALID_LANGUAGE)
    }
  }
}