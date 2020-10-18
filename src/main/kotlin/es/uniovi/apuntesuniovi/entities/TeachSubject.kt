package es.uniovi.apuntesuniovi.entities

import java.util.*
import javax.persistence.*

@Entity
class TeachSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0

    @ManyToOne
    lateinit var teacher: User

    @ManyToOne
    lateinit var subject: Subject

    @OneToMany(mappedBy = "teachSubject", cascade = [(CascadeType.ALL)])
    val registries: Set<TeachSubjectRegistry> = HashSet()

    var isCoordinator: Boolean = false
}