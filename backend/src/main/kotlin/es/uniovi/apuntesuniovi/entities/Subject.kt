package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = ""
        set(value) {
            if (value.isEmpty())
                throw IllegalArgumentException("El nombre de la asignatura no puede estar vacío")
            field = value
        }

    var course: Int = 0
        set(value) {
            if (value < 1)
                throw IllegalArgumentException("El curso de la asignatura no puede menor que 1")
            field = value
        }

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val teachSubjects: Set<TeachSubject> = HashSet()

    @OneToMany(mappedBy = "student", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val lessons: Set<Lesson> = HashSet()
}