package es.uniovi.apuntesuniovi.entities

import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
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
    var active: Boolean = false
    lateinit var img: String
    lateinit var birthDate: LocalDate

    @Column(unique = true)
    lateinit var username: String
    lateinit var password: String

    @Enumerated(EnumType.STRING)
    lateinit var role: RoleType

    @Enumerated(EnumType.STRING)
    lateinit var identificationType: IdentificationType

    lateinit var numberIdentification: String

    @OneToMany(mappedBy = "student", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()

    @OneToMany(mappedBy = "teacher", cascade = [(CascadeType.ALL)])
    val teachSubjects: Set<TeachSubject> = HashSet()

    fun setIdentificationType(identificationType: String?) {
        try {
            if (identificationType == null) {
                throw IllegalArgumentException(ExceptionMessages.NULL_IDENTIFICATION_TYPE)
            }
            this.identificationType = IdentificationType.valueOf(identificationType.toUpperCase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(ExceptionMessages.INVALID_IDENTIFICATION_TYPE)
        }
    }

    fun setRole(role: String?) {
        try {
            if (role == null) {
                throw IllegalArgumentException("sdfs")
            }
            this.role = RoleType.valueOf(role.toUpperCase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Rol no valido")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (email != other.email) return false
        if (username != other.username) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + username.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', surname='$surname', email='$email', phone='$phone'," +
                " active=$active, img='$img', birthDate=$birthDate, username='$username', " +
                " identificationType=$identificationType, numberIdentification='$numberIdentification')"
    }
}