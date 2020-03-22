package es.uniovi.apuntesuniovi

import es.uniovi.apuntesuniovi.entities.Student
import es.uniovi.apuntesuniovi.entities.Subject
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApuntesUnioviApplication

fun main(args: Array<String>) {
	runApplication<ApuntesUnioviApplication>(*args)
}
