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
  var name: String? = ""
    set(value) {
      if (ValidatorMaxLength(value, SubjectLimits.NAME).isValid()) {
        field = value
      } else {
        throw IllegalArgumentException(SubjectMessages.LIMIT_NAME)
      }
    }

  @Enumerated(EnumType.STRING)
  var subjectType: SubjectType = SubjectType.BASIC

  var active: Boolean = true

  @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
  val teachSubjects: Set<TeachSubject> = HashSet()

  @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
  val learnSubject: Set<LearnSubject> = HashSet()

  @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
  val lessons: Set<UnitSubject> = HashSet()

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
      throw IllegalArgumentException(SubjectMessages.INVALID_SUBJECT_TYPE)
    }
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Subject

    if (id != other.id) return false
    if (name != other.name) return false
    if (subjectType != other.subjectType) return false
    if (active != other.active) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id?.hashCode() ?: 0
    result = 31 * result + (name?.hashCode() ?: 0)
    result = 31 * result + subjectType.hashCode()
    result = 31 * result + active.hashCode()
    return result
  }


}