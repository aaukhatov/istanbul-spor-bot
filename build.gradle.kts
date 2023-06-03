import java.time.Clock
import java.time.Instant

plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "com.github.aaukhatov"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.9.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.github.aaukhatov.MainKt"
        attributes["Build-Time"] = "${Instant.now(Clock.systemUTC())}"
        attributes["Implementation-Version"] = "$archiveVersion"
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}