package es.uniovi.apuntesuniovi.entities

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Teacher(id: Long, name: String, surname: String, email: String, phone: String, active: Boolean, img: String, birthDate: String, username: String, password: String, role: String, identificationType: String, numberIdentification: String) : Person(
        id,
        name,
        surname,
        email,
        phone,
        active,
        img,
        birthDate,
        username,
        password,
        role,
        identificationType,
        numberIdentification
) {
    private var isAdmin: Boolean = false

    @OneToMany(mappedBy = "teacher", cascade = [(CascadeType.ALL)])
    private val teachSubjects: Set<TeachSubject> = HashSet()
}