package es.uniovi.apuntesuniovi.entities

import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.validators.impl.ValidatorCompositeAny
import es.uniovi.apuntesuniovi.validators.impl.ValidatorDni
import es.uniovi.apuntesuniovi.validators.impl.ValidatorNie
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0L

    lateinit var name: String
    lateinit var surname: String
    lateinit var email: String
    lateinit var phone: String
    var active: Boolean = true
    lateinit var img: String
    lateinit var birthDate: LocalDate

    @Column(unique = true)
    var username: String? = null
    lateinit var password: String

    @Enumerated(EnumType.STRING)
    lateinit var role: RoleType

    @Enumerated(EnumType.STRING)
    lateinit var identificationType: IdentificationType

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

    @OneToMany(mappedBy = "student", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()

    @OneToMany(mappedBy = "teacher", cascade = [(CascadeType.ALL)])
    val teachSubjects: Set<TeachSubject> = HashSet()

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