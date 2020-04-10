package es.uniovi.apuntesuniovi.entities

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Teacher : Person() {
    private var isAdmin: Boolean = false

    @OneToMany(mappedBy = "teacher", cascade = [(CascadeType.ALL)])
    private val teachSubjects: Set<TeachSubject> = HashSet()
}