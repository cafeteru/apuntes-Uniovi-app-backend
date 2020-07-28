package es.uniovi.apuntesuniovi.entities

import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.servicies.dates.DateService
import org.joda.time.LocalDate
import javax.persistence.*

@Entity
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var name: String,
        var surname: String,
        var email: String,
        var phone: String,
        var active: Boolean,
        var img: String,
        birthDate: String,
        var username: String,
        var password: String,
        @ManyToOne
        var role: Role?,
        identificationType: String,
        var numberIdentification: String
) {
    var birthDate: LocalDate? = DateService.stringToDate(birthDate)

    @Enumerated(EnumType.STRING)
    var identificationType: IdentificationType = IdentificationType.DNI

    @OneToMany(mappedBy = "student", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()

    private var isAdmin: Boolean = false

    @OneToMany(mappedBy = "teacher", cascade = [(CascadeType.ALL)])
    private val teachSubjects: Set<TeachSubject> = HashSet()

    init {
        setIdentificationType(identificationType)
    }

    /**
     * Cambia el tipo de identificación
     *
     * @param identificationType Tipo de identificación en formato texto
     */
    fun setIdentificationType(identificationType: String) {
        try {
            this.identificationType = IdentificationType.valueOf(
                    identificationType.toUpperCase())
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("Tipo de identificación no valido")
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Tipo de identificación no valido")
        }
    }
}