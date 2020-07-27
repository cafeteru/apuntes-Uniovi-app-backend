package es.uniovi.apuntesuniovi.entities

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Student(
        id: Long,
        name: String,
        surname: String,
        email: String,
        phone: String,
        active: Boolean,
        img: String,
        birthDate: String,
        username: String,
        password: String,
        role: String,
        identificationType: String,
        numberIdentification: String
) : Person(id, name, surname, email, phone, active, img, birthDate, username, password, role,
        identificationType, numberIdentification) {
    @OneToMany(mappedBy = "student", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()
}