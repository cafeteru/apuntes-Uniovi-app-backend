package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.CenterLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.CenterMessages
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxLength
import java.util.*
import javax.persistence.*

/**
 * Represents centers
 */
@Entity
class Center {
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

    @OneToMany(mappedBy = "center", cascade = [(CascadeType.ALL)])
    val careers: Set<Career> = HashSet()

    @OneToOne
    var address: Address? = null
}