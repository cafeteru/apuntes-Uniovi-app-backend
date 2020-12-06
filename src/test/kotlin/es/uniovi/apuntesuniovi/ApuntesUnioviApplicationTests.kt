package es.uniovi.apuntesuniovi

import es.uniovi.apuntesuniovi.entities.User
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

/**
 * Check class ApuntesUnioviApplicationTests
 */
@SpringBootTest
class ApuntesUnioviApplicationTests {

    /**
     * Check the application startup
     */
    @Test
    fun contextLoads() {
        assertNotNull(User())
    }

}
