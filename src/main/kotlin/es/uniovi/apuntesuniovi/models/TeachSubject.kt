package es.uniovi.apuntesuniovi.models

import javax.persistence.*

/**
 * Represents relationship between teachers and subjects
 */
@Entity
class TeachSubject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null

  @ManyToOne
  lateinit var teacher: User

  @ManyToOne
  lateinit var subject: Subject

  @OneToMany(mappedBy = "teachSubject", cascade = [(CascadeType.ALL)])
  val registries: Set<TeachSubjectRegistry> = HashSet()

  var isCoordinator: Boolean = false
}