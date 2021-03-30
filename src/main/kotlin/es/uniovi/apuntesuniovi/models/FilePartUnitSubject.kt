package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.FilePartUnitSubjectLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.FilePartUnitSubjectMessages
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxLength
import javax.persistence.*

/**
 * Represents files of the parts of the unit of a subject
 */
@Entity
class FilePartUnitSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(length = FilePartUnitSubjectLimits.CONTENT)
    var content: String? = null
        set(value) {
            if (ValidatorMaxLength(value, FilePartUnitSubjectLimits.CONTENT).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(FilePartUnitSubjectMessages.LIMIT_CONTENT)
            }
        }

    @ManyToOne
    var partUnitSubject: PartUnitSubject? = null
}