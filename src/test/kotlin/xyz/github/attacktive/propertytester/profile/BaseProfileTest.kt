package xyz.github.attacktive.propertytester.profile

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import kotlin.test.BeforeTest
import xyz.github.attacktive.propertytester.getProperties
import xyz.github.attacktive.propertytester.getPropertyMap

abstract class BaseProfileTest {
	@Autowired
	protected lateinit var environment: Environment

	protected lateinit var properties: Map<String, String?>
	protected lateinit var propertyMap: Map<String, Any>

	@BeforeTest
	fun getProperties() {
		properties = environment.getProperties()
		propertyMap = environment.getPropertyMap()

		println("[${environment.activeProfiles.joinToString()}] loaded properties: $properties")
		println("[${environment.activeProfiles.joinToString()}] loaded properties: $propertyMap")
	}
}
