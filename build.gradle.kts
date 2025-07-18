import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinGradlePluginVersion = "2.2.0"

	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("jvm") version kotlinGradlePluginVersion
	kotlin("plugin.spring") version kotlinGradlePluginVersion
}

group = "xyz.github.attacktive"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
	mavenCentral()
}

dependencies {
	testImplementation("org.springframework.boot:spring-boot-starter")
	testImplementation(kotlin("test"))
	testImplementation("org.springframework.boot", "spring-boot-starter-test") {
		exclude(group = "org.junit.jupiter")
		exclude(group = "org.mockito")
	}
	testImplementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.withType<KotlinCompile> {
	compilerOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget.set(JvmTarget.JVM_21)
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	useJUnitPlatform()
}
