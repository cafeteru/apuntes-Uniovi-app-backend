package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.models.types.SubjectType
import javax.persistence.*

/**
 * Represents option ETCS
 */
@Entity
class OptionETCS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    // TODO ADD LIMIT AND ALL ENTITY
    var etcs: Int = 0

    // TODO CHECK
    var subjectType: SubjectType? = null

    @ManyToOne
    var career: Career? = null
}