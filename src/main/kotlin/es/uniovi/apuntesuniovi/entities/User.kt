package es.uniovi.apuntesuniovi.entities

import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.constants.DatabaseLimits
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.validators.impl.*
import java.time.LocalDate
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0L

    @Column(length = DatabaseLimits.USER_NAME)
    var name: String? = null
        set(value) {
            if (ValidatorMaxSize(value, DatabaseLimits.USER_NAME).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(ExceptionMessages.LIMIT_USER_NAME)
            }
        }

    @Column(length = DatabaseLimits.USER_SURNAME)
    var surname: String? = null
        set(value) {
            if (ValidatorMaxSize(value, DatabaseLimits.USER_SURNAME).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(ExceptionMessages.LIMIT_USER_SURNAME)
            }
        }

    @Column(length = DatabaseLimits.USER_EMAIL)
    var email: String? = null
        set(value) {
            val validator = ValidatorCompositeAll()
            validator.add(ValidatorEmail(value))
            validator.add(ValidatorMaxSize(value, DatabaseLimits.USER_EMAIL))
            if (validator.isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(ExceptionMessages.INVALID_EMAIL)
            }
        }

    @Column(length = DatabaseLimits.USER_EMAIL)
    var phone: String? = null
        set(value) {
            if (ValidatorPhone(value).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(ExceptionMessages.LIMIT_USER_EMAIL)
            }
        }

    var active: Boolean = true

    var img: String? = null
        set(value) {
            if (ValidatorMaxSize(value, DatabaseLimits.USER_IMG).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(ExceptionMessages.LIMIT_USER_IMG)
            }
        }

    var birthDate: LocalDate? = null
        set(value) {
            if (ValidatorLaterDayToday(value).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(ExceptionMessages.LIMIT_USER_BIRTH_DATE)
            }
        }

    @Column(unique = true, length = DatabaseLimits.USER_USERNAME)
    var username: String? = null
        set(value) {
            if (ValidatorMaxSize(value, DatabaseLimits.USER_USERNAME).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(ExceptionMessages.LIMIT_USER_USERNAME)
            }
        }

    @Column(length = DatabaseLimits.USER_PASSWORD)
    var password: String? = null
        set(value) {
            if (ValidatorMaxSize(value, DatabaseLimits.USER_PASSWORD).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(ExceptionMessages.LIMIT_USER_PASSWORD)
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
                throw IllegalArgumentException(ExceptionMessages.INVALID_IDENTIFICATION_NUMBER)
            }
        }

    @OneToOne
    lateinit var address: Address

    fun setIdentificationType(identificationType: String?) {
        if (identificationType == null) {
            throw IllegalArgumentException(ExceptionMessages.NULL_IDENTIFICATION_TYPE)
        }
        try {
            this.identificationType = IdentificationType.valueOf(identificationType.toUpperCase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(ExceptionMessages.INVALID_IDENTIFICATION_TYPE)
        }
    }

    fun setRole(role: String?) {
        if (role == null) {
            throw IllegalArgumentException(ExceptionMessages.NULL_ROLE_TYPE)
        }
        try {
            this.role = RoleType.valueOf(role.toUpperCase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(ExceptionMessages.INVALID_ROLE_TYPE)
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