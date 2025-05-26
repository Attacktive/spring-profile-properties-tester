package com.github.attacktive.propertytester

import com.github.attacktive.propertytester.printer.PrintingService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringProfilePropertiesTesterApplication(private val printingService: PrintingService): CommandLineRunner {
	// Please forgive me. I couldn't resist.
	@Suppress("LocalVariableName")
	//override fun run(vararg `whateverDude 😏`: String) = printingService.printProperties()
	override fun run(vararg `whateverDude 😏`: String) = printingService.testMalformed()
}

fun main() {
	runApplication<SpringProfilePropertiesTesterApplication>()
}
