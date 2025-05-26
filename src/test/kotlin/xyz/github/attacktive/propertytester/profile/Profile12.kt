package xyz.github.attacktive.propertytester.profile

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("profile1", "profile2")
class Profile12: BaseProfileTest() {
	@Test
	fun test_getProperties() {
		// It's not 3 because each value in the "array" has its own key such as "a.c[0]", etc.
		assertEquals(6, properties.size)

		assertNull(properties["foo.a"])
		assertEquals("profile1-a.b", properties["foo.a.b"])

		assertNull(properties["foo.a.c"])
		assertEquals("profile2-a.c1", properties["foo.a.c[0]"])
		assertEquals("profile2-a.c2", properties["foo.a.c[1]"])
		assertEquals("profile2-a.c3", properties["foo.a.c[2]"])

		assertEquals("default: d", properties["foo.d"])
		assertEquals("profile1-e (new added)", properties["foo.e"])
	}

	@Test
	fun test_getPropertyMap() {
		assertEquals(3, propertyMap.size)

		assertNull(propertyMap["foo"])
		assertNotNull(propertyMap["a"])
		assertNotNull(propertyMap["d"])
		assertNotNull(propertyMap["e"])

		val a = propertyMap["a"]
		assertTrue(a is Map<*, *>)
		assertEquals("profile1-a.b", a["b"])

		val ac = a["c"]
		assertTrue(ac is Map<*, *>)
		assertEquals("profile2-a.c1", ac["0"])
		assertEquals("profile2-a.c2", ac["1"])
		assertEquals("profile2-a.c3", ac["2"])

		assertEquals("default: d", propertyMap["d"])
		assertEquals("profile1-e (new added)", propertyMap["e"])
	}
}
