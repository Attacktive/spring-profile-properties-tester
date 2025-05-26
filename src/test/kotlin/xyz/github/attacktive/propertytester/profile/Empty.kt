package xyz.github.attacktive.propertytester.profile

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("empty")
class Empty: BaseProfileTest() {
	@Test
	fun test_getProperties() {
		// It's not 3 because each value in the "array" has its own key such as "a.c[0]", etc.
		assertEquals(5, properties.size)

		assertNull(properties["foo.a"])
		assertEquals("default-a.b", properties["foo.a.b"])

		assertNull(properties["foo.a.c"])
		assertEquals("default-a.c1", properties["foo.a.c[0]"])
		assertEquals("default-a.c2", properties["foo.a.c[1]"])
		assertEquals("default-a.c3", properties["foo.a.c[2]"])

		assertEquals("default: d", properties["foo.d"])
	}

	@Test
	fun test_getPropertyMap() {
		assertEquals(2, propertyMap.size)

		assertNull(propertyMap["foo"])
		assertNotNull(propertyMap["a"])
		assertNotNull(propertyMap["d"])

		val a = propertyMap["a"]
		assertTrue(a is Map<*, *>)
		assertEquals("default-a.b", a["b"])

		val ac = a["c"]
		assertTrue(ac is Map<*, *>)
		assertEquals("default-a.c1", ac["0"])
		assertEquals("default-a.c2", ac["1"])
		assertEquals("default-a.c3", ac["2"])

		assertEquals("default: d", propertyMap["d"])
	}
}
