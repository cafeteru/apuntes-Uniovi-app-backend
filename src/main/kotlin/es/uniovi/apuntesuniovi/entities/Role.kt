package es.uniovi.apuntesuniovi.entities

import javax.persistence.*

@Entity
class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var name: String,
        var active: Boolean
) {
    @OneToMany(mappedBy = "role", cascade = [(CascadeType.ALL)])
    val users: Set<User> = HashSet()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Role

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
    
    override fun toString(): String {
        return "Role(id=$id, name='$name', active=$active)"
    }
}