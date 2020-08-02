package es.uniovi.apuntesuniovi.entities

import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.entities.types.RoleType
import java.util.*
import javax.persistence.*

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    lateinit var name: String
    lateinit var surname: String
    lateinit var email: String
    lateinit var phone: String
    var active: Boolean = false
    lateinit var img: String
    lateinit var birthDate: Date

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
    private val teachSubjects: Set<TeachSubject> = HashSet()

    /**
     * Cambia el tipo de identificación
     *
     * @param identificationType Tipo de identificación en formato texto
     */
    fun setIdentificationType(identificationType: String?) {
        try {
            if (identificationType == null) {
                throw IllegalArgumentException("sdfs")
            }
            this.identificationType = IdentificationType.valueOf(identificationType.toUpperCase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Tipo de identificación no valido")
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
        return "User(id=$id, name='$name', surname='$surname', email='$email', phone='$phone', active=$active," +
                " img='$img', birthDate=$birthDate, username='$username', password='$password'," +
                " identificationType=$identificationType, numberIdentification='$numberIdentification')"
    }
}