package es.uniovi.apuntesuniovi.entities

import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.servicies.dates.DateService
import org.joda.time.LocalDate
import javax.persistence.*


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
open class Person(@Id
                  @GeneratedValue(strategy = GenerationType.IDENTITY)
                  open var id: Long?,
                  open var name: String,
                  open var surname: String,
                  open var email: String,
                  open var phone: String,
                  open var active: Boolean,
                  open var img: String,
                  birthDate: String,
                  open var username: String,
                  open var password: String,
                  role: String,
                  identificationType: String,
                  open var numberIdentification: String
) {
    open var birthDate: LocalDate? = DateService.stringToDate(birthDate)

    @Enumerated(EnumType.STRING)
    open var role: RoleType = RoleType.STUDENT

    @Enumerated(EnumType.STRING)
    open var identificationType: IdentificationType = IdentificationType.DNI

    init {
        setRole(role)
        setIdentificationType(identificationType)
    }


    fun setIdentificationType(identificationType: String) {
        try {
            this.identificationType = IdentificationType.valueOf(
                    identificationType.toUpperCase())
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("Tipo de identificación no " +
                    "valido")
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Tipo de identificación no " +
                    "valido")
        }
    }

    fun setRole(role: String) {
        try {
            this.role = RoleType.valueOf(role.toUpperCase())
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("Rol no valido")
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Rol no valido")
        }
    }

}