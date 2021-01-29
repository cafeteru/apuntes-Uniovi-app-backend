package es.uniovi.apuntesuniovi.models

import javax.persistence.*

/**
 * Represents relationship between students and subjects
 */
@Entity
class LearnSubject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null

  @ManyToOne
  lateinit var student: User

  @ManyToOne
  lateinit var subject: Subject

  var pass: Boolean = false
}