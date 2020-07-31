package es.uniovi.apuntesuniovi.entities

import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import java.util.*
import javax.persistence.*

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

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

    @ManyToOne
    var role: Role? = null
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
    fun setIdentificationType(identificationType: String?): IdentificationType {
        try {
            if (identificationType == null) {
                throw IllegalArgumentException("sdfs")
            }
            return IdentificationType.valueOf(identificationType.toUpperCase())

        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Tipo de identificación no valido")
        }
    }
}