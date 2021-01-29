package es.uniovi.apuntesuniovi.models

import java.util.*
import javax.persistence.*

/**
 * Represents tests
 */
@Entity
class Test {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null

  @ManyToMany
  val units: Set<UnitSubject> = HashSet()

  @ManyToMany(mappedBy = "tests", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
  val questions: Set<Question> = HashSet()
}