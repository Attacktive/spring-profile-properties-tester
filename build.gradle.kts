import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("jvm") version "2.1.21"
	kotlin("plugin.spring") version "2.1.21"
}

group = "com.github.attacktive"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
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
