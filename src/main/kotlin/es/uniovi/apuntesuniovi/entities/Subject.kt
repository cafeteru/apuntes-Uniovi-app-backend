package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Subject(
        id: Long?,
        name: String,
        course: Int
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    internal var id: Long? = id
        set(value) {
            if (value != null && value <= 0)
                throw IllegalArgumentException("El id de la asignatura no puede ser menor que 1")
            field = value
        }

    internal var name: String = name
        set(value) {
            if (value.isEmpty())
                throw IllegalArgumentException("El nombre de la asignatura no puede estar vacío")
            field = value
        }

    internal var course: Int = course
        set(value) {
            if (value < 1)
                throw IllegalArgumentException("El curso de la asignatura no puede ser menor que 1")
            field = value
        }

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    internal val teachSubjects: Set<TeachSubject> = HashSet()

    @OneToMany(mappedBy = "student", cascade = [(CascadeType.ALL)])
    internal val learnSubject: Set<LearnSubject> = HashSet()

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    internal val lessons: Set<Lesson> = HashSet()
}