package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class TeachSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @ManyToOne
    private var teacher: User? = null

    @ManyToOne
    private var subject: Subject? = null

    @OneToMany(mappedBy = "teachSubject", cascade = [(CascadeType.ALL)])
    private val registries: Set<TeachSubjectRegistry> = HashSet()

    private var isCoordinator: Boolean = false
}