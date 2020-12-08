package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.CareerLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.CareerMessages
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.types.IdentificationType
import es.uniovi.apuntesuniovi.models.types.LanguageType
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxLength
import java.util.*
import javax.persistence.*

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

    // TODO ADD LIMIT
    var code: String? = null
    // TODO ADD LIMIT
    var yearImplantation: Int? = null

    // TODO review
    var ISCED: String? = null
    // TODO review
    var ECTS: Int? = null

    // TODO CHANGE TO LIST
    var languages: LanguageType? = null

    @ManyToOne
    var center: Center? = null

    // TODO review
    @ManyToOne
    var configurationECTS: ConfigurationECTS? = null

    @OneToMany(mappedBy = "career", cascade = [(CascadeType.ALL)])
    val courses: Set<Course> = HashSet()

    /**
     * Set identificationType according to a text
     *
     * @param identificationType Text
     * @throws IllegalArgumentException Invalid text
     */
    fun setIdentificationType(identificationType: String?) {
        if (identificationType != null) {
            try {
                this.languages = LanguageType.valueOf(
                    identificationType.toUpperCase()
                )
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException(UserMessages.INVALID_IDENTIFICATION_TYPE)
            }
        } else {
            this.languages = null
        }
    }
}