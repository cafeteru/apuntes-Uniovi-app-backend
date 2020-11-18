package es.uniovi.apuntesuniovi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Main class that launches the application
 */
@SpringBootApplication
class ApuntesUnioviApplication

/**
 * Launches the application
 */
fun main(args: Array<String>) {
    runApplication<ApuntesUnioviApplication>(args.contentToString())
}
