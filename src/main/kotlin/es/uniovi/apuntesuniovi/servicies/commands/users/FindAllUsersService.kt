package es.uniovi.apuntesuniovi.servicies.commands.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.UserRepository

/**
 * Return all users in service layer
 */
class FindAllUsersService(
    private val userRepository: UserRepository
) : AbstractCommand<List<User>>() {
    override fun execute(): List<User> {
        logService.info("execute() - start")
        val list = userRepository.findAll()
        logService.info("execute() - end")
        return list
    }
}