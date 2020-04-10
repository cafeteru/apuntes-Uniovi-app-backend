package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Subject(id: Long?, name: String, course: Int) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id
        set(value) {
            if (value != null && value <= 0)
                throw IllegalArgumentException("El id de la asignatura no puede ser menor que 1")
            field = value
        }

    var name: String = name
        set(value) {
            if (value.isEmpty())
                throw IllegalArgumentException("El nombre de la asignatura no puede estar vacío")
            field = value
        }

    var course: Int = course
        set(value) {
            if (value < 1)
                throw IllegalArgumentException("El curso de la asignatura no puede ser menor que 1")
            field = value
        }

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val teachSubjects: Set<TeachSubject> = HashSet()

    @OneToMany(mappedBy = "student", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val lessons: Set<Lesson> = HashSet()
}