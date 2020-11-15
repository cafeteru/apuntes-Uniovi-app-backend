package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import java.time.LocalDate

/**
 * Service to create mock data of the dto UserDto
 */
class MockUserDtoCreator : MockCreator<UserDto> {
    override fun create(): UserDto {
        return UserDto(
                id = 3,
                name = "admin",
                surname = "admin",
                active = true,
                birthDate = LocalDate.now(),
                email = "admin@admin.com",
                identificationType = IdentificationType.DNI.toString(),
                img = "",
                numberIdentification = "16207928N",
                password = "admin",
                phone = "",
                username = "admin",
                role = RoleType.ADMIN.toString()
        )
    }
}