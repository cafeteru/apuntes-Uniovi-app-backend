package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var name: String = ""
    var course: Int = 0

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val teachSubjects: Set<TeachSubject> = HashSet()

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val learnSubject: Set<LearnSubject> = HashSet()

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.ALL)])
    val lessons: Set<Lesson> = HashSet()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Subject

        if (name != other.name) return false
        if (course != other.course) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + course
        return result
    }

    override fun toString(): String {
        return "Subject(id=$id, name='$name', course=$course)"
    }
}