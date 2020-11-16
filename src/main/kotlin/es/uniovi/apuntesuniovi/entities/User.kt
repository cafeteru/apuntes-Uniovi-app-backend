package es.uniovi.apuntesuniovi.entities

import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.validators.impl.ValidatorCompositeAny
import es.uniovi.apuntesuniovi.validators.impl.ValidatorDni
import es.uniovi.apuntesuniovi.validators.impl.ValidatorEmail
import es.uniovi.apuntesuniovi.validators.impl.ValidatorNie
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0L

    var name: String? = null
    var surname: String? = null
    var email: String? = null
        set(value) {
            val validator = ValidatorEmail(value)
            if (validator.isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(ExceptionMessages.INVALID_EMAIL)
            }
        }

    var phone: String? = null
    var active: Boolean = true
    var img: String? = null
    var birthDate: LocalDate? = null

    @Column(unique = true)
    var username: String? = null
    var password: String? = null

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