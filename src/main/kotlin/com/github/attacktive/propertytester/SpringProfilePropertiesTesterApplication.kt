package com.github.attacktive.propertytester

import com.github.attacktive.propertytester.printer.PrintingService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringProfilePropertiesTesterApplication(private val printingService: PrintingService): CommandLineRunner {
	override fun run(vararg args: String?) = printingService.printProperties()
}

fun main(args: Array<String>) {
	runApplication<SpringProfilePropertiesTesterApplication>(*args)
}
