package com.github.attacktive.propertytester.printer

import org.slf4j.LoggerFactory
import org.springframework.boot.env.OriginTrackedMapPropertySource
import org.springframework.core.env.AbstractEnvironment
import org.springframework.core.env.Environment
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
		logger.info("propertyNames:\n${propertyNames.joinToString("\n")}")

		val computedProperties = getComputedProperties(propertyNames.toList().sorted())
		val computedPropertiesInString = computedProperties.map { "${it.key}: ${it.value}" }
			.joinToString("\n")

		logger.info("computedProperties:\n$computedPropertiesInString")
	}

	private fun getRawPropertyNames(): Set<String> = (environment as AbstractEnvironment).propertySources
		.filterIsInstance<OriginTrackedMapPropertySource>()
		.map { it.source }
		.flatMap { it.keys }
		.toSet()

	private fun getComputedProperties(propertyNames: Collection<String>): Map<String, String?> = propertyNames.associateWith { environment.getProperty(it) }
}
