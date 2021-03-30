package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.springframework.util.Assert

/**
 * Change a user's language
 */
class ChangeLanguageUser(
    private val userRepository: UserRepository,
    private val username: String,
    private val language: String
) : AbstractCommand<Boolean>() {

    override fun execute(): Boolean {
        logService.info("execute() - start")
        val optional = userRepository.findByUsername(username)
        Assert.isTrue(optional.isPresent, UserMessages.NOT_FOUND)
        val user = optional.get()
        user.setLanguage(language)
        userRepository.save(user)
        logService.info("execute() - end")
        return true
    }
}