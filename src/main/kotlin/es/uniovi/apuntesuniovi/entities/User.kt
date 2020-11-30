package es.uniovi.apuntesuniovi.entities

import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.constants.DatabaseLimits
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.UserMessages
import es.uniovi.apuntesuniovi.validators.impl.*
import java.time.LocalDate
import javax.persistence.*

/**
 * Represents users
 */
@Entity
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @Column(length = DatabaseLimits.USER_NAME)
    var name: String? = null
        set(value) {
            if (ValidatorMaxLength(value, DatabaseLimits.USER_NAME).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.LIMIT_NAME)
            }
        }

    @Column(length = DatabaseLimits.USER_SURNAME)
    var surname: String? = null
        set(value) {
            if (ValidatorMaxLength(value, DatabaseLimits.USER_SURNAME).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.LIMIT_SURNAME)
            }
        }

    @Column(length = DatabaseLimits.USER_EMAIL)
    var email: String? = null
        set(value) {
            val validator = ValidatorCompositeAll()
            validator.add(ValidatorEmail(value))
            validator.add(ValidatorMaxLength(value, DatabaseLimits.USER_EMAIL))
            if (validator.isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.INVALID_EMAIL)
            }
        }

    var phone: String? = null
        set(value) {
            if (ValidatorPhone(value).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.INVALID_PHONE)
            }
        }

    var active: Boolean = true

    var img: String? = null
        set(value) {
            if (ValidatorMaxLength(value, DatabaseLimits.USER_IMG).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.LIMIT_IMG)
            }
        }

    var birthDate: LocalDate? = null
        set(value) {
            if (ValidatorLaterDayToday(value).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.LIMIT_BIRTH_DATE)
            }
        }

    @Column(unique = true, length = DatabaseLimits.USER_USERNAME)
    var username: String? = null
        set(value) {
            if (ValidatorMaxLength(value, DatabaseLimits.USER_USERNAME).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.LIMIT_USERNAME)
            }
        }

    @Column(length = DatabaseLimits.USER_PASSWORD)
    var password: String? = null
        set(value) {
            if (ValidatorMaxLength(value, DatabaseLimits.USER_PASSWORD).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.LIMIT_PASSWORD)
            }
        }

    @Enumerated(EnumType.STRING)
    var role: RoleType = RoleType.STUDENT

    @Enumerated(EnumType.STRING)
    var identificationType: IdentificationType = IdentificationType.DNI

    var numberIdentification: String? = null
        set(value) {
            val validator = ValidatorCompositeAny()
            validator.add(ValidatorDni(value))
            validator.add(ValidatorNie(value))
            if (validator.isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.INVALID_IDENTIFICATION_NUMBER)
            }
        }

    @OneToOne
    lateinit var address: Address

    /**
     * Set identificationType according to a text
     *
     * @param identificationType Text
     * @throws IllegalArgumentException Invalid text
     */
    fun setIdentificationType(identificationType: String?) {
        if (identificationType == null) {
            throw IllegalArgumentException(UserMessages.NULL_IDENTIFICATION_TYPE)
        }
        try {
            this.identificationType = IdentificationType.valueOf(identificationType.toUpperCase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(UserMessages.INVALID_IDENTIFICATION_TYPE)
        }
    }

    /**
     * Set role according to a text
     *
     * @param role Text
     * @throws IllegalArgumentException Invalid text
     */
    fun setRole(role: String?) {
        if (role == null) {
            throw IllegalArgumentException(UserMessages.NULL_ROLE_TYPE)
        }
        try {
            this.role = RoleType.valueOf(role.toUpperCase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(UserMessages.INVALID_ROLE_TYPE)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (username != other.username) return false
        if (numberIdentification != other.numberIdentification) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + numberIdentification.hashCode()
        return result
    }
}