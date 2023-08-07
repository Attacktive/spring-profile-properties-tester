package com.github.attacktive.propertytester

import com.github.attacktive.propertytester.printer.PrintingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringProfilePropertiesTesterApplication: CommandLineRunner {
	@Autowired
	private lateinit var printingService: PrintingService

	override fun run(vararg args: String?) {
		printingService.printProperties()
	}
}

fun main(args: Array<String>) {
	runApplication<SpringProfilePropertiesTesterApplication>(*args)
}
