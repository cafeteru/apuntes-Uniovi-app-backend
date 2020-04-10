package es.uniovi.apuntesuniovi.entities

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Student : Person() {
    @OneToMany(mappedBy = "student", cascade = [(CascadeType.ALL)])
    private val learnSubject: Set<LearnSubject> = HashSet()
}