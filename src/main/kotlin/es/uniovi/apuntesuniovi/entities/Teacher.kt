package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Teacher : User() {
    @OneToMany(mappedBy = "teacher", cascade = [(CascadeType.ALL)])
    val teachSubjects: Set<TeachSubject> = HashSet()
}