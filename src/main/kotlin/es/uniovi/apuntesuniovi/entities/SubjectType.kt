package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class SubjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0

    lateinit var name: String

    @OneToMany(mappedBy = "subjectType", cascade = [(CascadeType.ALL)])
    val subjects: Set<Subject> = HashSet()

    @ManyToMany(mappedBy = "subjectTypes", cascade = [(CascadeType.ALL)])
    var valuesConfigurationETCS: Set<OptionETCS> = HashSet()
}