package es.uniovi.apuntesuniovi.models

import es.uniovi.apuntesuniovi.infrastructure.constants.database.UserLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.types.IdentificationType
import es.uniovi.apuntesuniovi.models.types.LanguageType
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.validators.impl.*
import io.github.cafeteru.validator_lib.DniValidator
import io.github.cafeteru.validator_lib.EmailValidator
import io.github.cafeteru.validator_lib.NieValidator
import io.github.cafeteru.validator_lib.PhoneValidator
import java.time.LocalDate
import java.util.*
import javax.persistence.*

/**
 * Represents users
 */
@Entity
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(length = UserLimits.NAME)
    var name: String? = null
        set(value) {
            if (ValidatorMaxLength(value, UserLimits.NAME).isValid) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.LIMIT_NAME)
            }
        }

    @Column(length = UserLimits.SURNAME)
    var surname: String? = null
        set(value) {
            if (ValidatorMaxLength(value, UserLimits.SURNAME).isValid) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.LIMIT_SURNAME)
            }
        }

    @Column(length = UserLimits.EMAIL)
    var email: String? = null
        set(value) {
            val validator = ValidatorCompositeAll()
            validator.add(EmailValidator(value))
            validator.add(ValidatorMaxLength(value, UserLimits.EMAIL))
            if (validator.isValid) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.INVALID_EMAIL)
            }
        }

    var phone: String? = null
        set(value) {
            if (PhoneValidator(value).isValid) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.INVALID_PHONE)
            }
        }

    var active: Boolean = true

    @Column(length = UserLimits.IMG)
    var img: String? = null
        set(value) {
            if (ValidatorMaxLength(value, UserLimits.IMG).isValid) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.LIMIT_IMG)
            }
        }

    var birthDate: LocalDate? = null
        set(value) {
            if (ValidatorLaterDayToday(value).isValid) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.LIMIT_BIRTH_DATE)
            }
        }

    @Column(unique = true, length = UserLimits.USERNAME)
    var username: String? = null
        set(value) {
            if (ValidatorMaxLength(value, UserLimits.USERNAME).isValid) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.LIMIT_USERNAME)
            }
        }

    @Column(length = UserLimits.PASSWORD)
    var password: String? = null
        set(value) {
            if (ValidatorMaxLength(value, UserLimits.PASSWORD).isValid) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.LIMIT_PASSWORD)
            }
        }

    @Enumerated(EnumType.STRING)
    var language: LanguageType = LanguageType.ES

    @Enumerated(EnumType.STRING)
    var role: RoleType? = null

    @Enumerated(EnumType.STRING)
    var identificationType: IdentificationType? = null

    var numberIdentification: String? = null
        set(value) {
            val validator = ValidatorCompositeAny()
            validator.add(DniValidator(value))
            validator.add(NieValidator(value))
            if (validator.isValid) {
                field = value
            } else {
                throw IllegalArgumentException(UserMessages.INVALID_IDENTIFICATION_NUMBER)
            }
        }

    @OneToOne
    var address: Address? = null

    /**
     * Set identificationType according to a text
     *
     * @param language Text
     * @throws IllegalArgumentException Invalid text
     */
    fun setLanguage(language: String?) {
        if (language != null) {
            try {
                this.language = LanguageType.valueOf(language.uppercase(Locale.getDefault()))
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException(UserMessages.INVALID_LANGUAGE)
            }
        }
    }

    /**
     * Set identificationType according to a text
     *
     * @param identificationType Text
     * @throws IllegalArgumentException Invalid text
     */
    fun setIdentificationType(identificationType: String?) {
        if (identificationType != null) {
            try {
                this.identificationType = IdentificationType.valueOf(
                    identificationType.uppercase(Locale.getDefault())
                )
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException(UserMessages.INVALID_IDENTIFICATION_TYPE)
            }
        } else {
            this.identificationType = null
        }
    }

    /**
     * Set role according to a text
     *
     * @param role Text
     * @throws IllegalArgumentException Invalid text
     */
    fun setRole(role: String?) {
        if (role != null) {
            try {
                this.role = RoleType.valueOf(role.uppercase(Locale.getDefault()))
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException(UserMessages.INVALID_ROLE_TYPE)
            }
        } else {
            this.role = null
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
