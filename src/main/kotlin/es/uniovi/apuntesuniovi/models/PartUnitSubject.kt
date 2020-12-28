package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.PartUnitSubjectLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.PartUnitSubjectMessages
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxLength
import java.util.*
import javax.persistence.*

/**
 * Represents parts of the unit of a subject
 */
@Entity
class PartUnitSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(length = PartUnitSubjectLimits.NAME)
    var name: String? = null
        set(value) {
            if (ValidatorMaxLength(value, PartUnitSubjectLimits.NAME).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(PartUnitSubjectMessages.LIMIT_NAME)
            }
        }

    @Column(length = PartUnitSubjectLimits.DESCRIPTION)
    var description: String? = null
        set(value) {
            if (ValidatorMaxLength(value, PartUnitSubjectLimits.DESCRIPTION).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(PartUnitSubjectMessages.LIMIT_DESCRIPTION)
            }
        }

    @ManyToOne
    var unitSubject: UnitSubject? = null

    @ManyToOne
    var father: PartUnitSubject? = null

    @OneToMany(mappedBy = "father", cascade = [(CascadeType.ALL)])
    val children: Set<PartUnitSubject> = HashSet()

    @OneToMany(mappedBy = "partUnitSubject", cascade = [(CascadeType.ALL)])
    val files: Set<FilePartUnitSubject> = HashSet()
}