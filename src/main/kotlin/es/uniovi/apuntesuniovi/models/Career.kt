package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.CareerLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.CareerMessages
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
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
    var ECTS: Int? = null
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