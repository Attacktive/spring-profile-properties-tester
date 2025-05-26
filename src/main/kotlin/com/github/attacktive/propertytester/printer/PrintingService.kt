package com.github.attacktive.propertytester.printer

import org.slf4j.LoggerFactory
import org.springframework.boot.env.OriginTrackedMapPropertySource
import org.springframework.core.env.AbstractEnvironment
import org.springframework.core.env.Environment
import org.springframework.core.env.getProperty
import org.springframework.stereotype.Service

@Service
class PrintingService(private val environment: Environment) {
	companion object {
		private val logger = LoggerFactory.getLogger(PrintingService::class.java)
	}

	fun printProperties() {
		val activeProfiles = environment.activeProfiles.joinToString(", ")
		logger.info("active profiles: $activeProfiles")

		val propertyNames = getRawPropertyNames()
		logger.info("propertyNames:")
		logger.info(propertyNames.joinToString("\n"))

		val computedProperties = propertyNames.toList()
			.sorted()
			.associateWith { environment.getProperty(it) }

		val computedPropertiesInString = computedProperties.map { "${it.key}: ${it.value}" }
			.joinToString("\n")

		logger.info("computedProperties:\n$computedPropertiesInString")
	}

	fun testMalformed() {
		val stringProperty = environment.getProperty<String>("a.b")
		logger.info("a.b: $stringProperty")

		val listProperty = environment.getProperty<List<String>>("a.b")
		logger.info("a.b: $listProperty")
	}

	private fun getRawPropertyNames(): Set<String> {
		if (environment !is AbstractEnvironment) {
			throw IllegalStateException("The environment is supposed to be of ${AbstractEnvironment::class.java.name}, but actually is ${environment.javaClass.name}")
		}

		return environment.propertySources
			.filterIsInstance<OriginTrackedMapPropertySource>()
			.map { it.source }
			.flatMap { it.keys }
			.toSet()
	}
}
