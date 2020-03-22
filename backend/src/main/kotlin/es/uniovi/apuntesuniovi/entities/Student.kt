package es.uniovi.apuntesuniovi.entities

import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated


@Entity
class Student : Person() {
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