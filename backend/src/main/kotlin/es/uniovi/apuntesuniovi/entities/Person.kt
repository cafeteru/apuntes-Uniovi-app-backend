package es.uniovi.apuntesuniovi.entities

import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import java.time.LocalDate
import javax.persistence.*


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
open class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
    private var name: String = ""
    private var surname: String = ""
    private var email: String = ""
    private var phone: String = ""
    private var active: Boolean = true
    private var img: String = ""
    private var birthDate: LocalDate? = null

    @Enumerated(EnumType.STRING)
    private var identificationType: IdentificationType = IdentificationType.DNI
    private var numberIdentification: String = ""

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
}