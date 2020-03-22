package es.uniovi.apuntesuniovi.entities

import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Teacher : Person() {
    @Enumerated(EnumType.STRING)
    private var identificationType: IdentificationType = IdentificationType.DNI
    private var numberIdentification: String = ""
    private var isAdmin: Boolean = false
}