package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.UnitSubjectLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.UnitSubjectMessages
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxLength
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMinValue
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Min

/**
 * Represents units of a subject
 */
@Entity
class UnitSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var subject: Subject? = null

    @Column(length = UnitSubjectLimits.NAME)
    var name: String? = null
        set(value) {
            if (ValidatorMaxLength(value, UnitSubjectLimits.NAME).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(UnitSubjectMessages.LIMIT_NAME)
            }
        }

    @Min(UnitSubjectLimits.POSITION_MIN.toLong())
    var position: Int? = null
        set(value) {
            value?.let {
                if (!ValidatorMinValue(it, UnitSubjectLimits.POSITION_MIN).isValid()) {
                    throw IllegalArgumentException(UnitSubjectMessages.LIMIT_POSITION_MIN)
                }
            }
            field = value
        }

    @ManyToMany(mappedBy = "units", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    val tests: Set<Test> = HashSet()
}