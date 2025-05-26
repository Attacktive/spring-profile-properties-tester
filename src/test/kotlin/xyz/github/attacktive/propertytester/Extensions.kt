package xyz.github.attacktive.propertytester

import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.bind.Binder
import org.springframework.boot.env.OriginTrackedMapPropertySource
import org.springframework.core.env.AbstractEnvironment
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.Environment

fun Environment.getProperties(): Map<String, String?> {
	if (this !is AbstractEnvironment) {
		throw IllegalStateException("The environment is supposed to be of ${AbstractEnvironment::class.java.name}, but actually is ${javaClass.name}")
	}

	val logger = LoggerFactory.getLogger(Environment::class.java)

	val activeProfiles = activeProfiles.joinToString(", ")
	logger.debug("active profiles: $activeProfiles")

	val propertyNames = propertySources
		.filterIsInstance<OriginTrackedMapPropertySource>()
		.map { it.source }
		.flatMap { it.keys }
		.toSet()

	logger.debug("propertyNames: ${propertyNames.joinToString()}")

	return propertyNames.toList()
		.sorted()
		.associateWith { getProperty(it) }
}

fun Environment.getPropertyMap(): Map<String, Any> {
	if (this !is ConfigurableEnvironment) {
		throw IllegalStateException("The environment is supposed to be of ${ConfigurableEnvironment::class.java.name}, but actually is ${javaClass.name}")
	}

	return Binder.get(this)
		.bind("foo", Map::class.java)
		.orElse(mapOf<String, Any>()) as Map<String, Any>
}
