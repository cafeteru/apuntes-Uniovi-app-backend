package es.uniovi.apuntesuniovi.models

import java.util.*
import javax.persistence.*

/**
 * Represents types of subjects
 */
@Entity
class SubjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    lateinit var name: String

    @OneToMany(mappedBy = "subjectType", cascade = [(CascadeType.ALL)])
    val subjects: Set<Subject> = HashSet()

    @ManyToMany(mappedBy = "subjectTypes", cascade = [(CascadeType.ALL)])
    var valuesConfigurationETCS: Set<OptionETCS> = HashSet()
}