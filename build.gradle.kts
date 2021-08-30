import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
}

group = "me.nick"
version = "1.0-SNAPSHOT"
val koinVersion = "3.1.2"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // Koin core features
    implementation("io.insert-koin:koin-core:$koinVersion")
// Koin test features
    testImplementation("io.insert-koin:koin-test:$koinVersion")

    implementation("org.jsoup:jsoup:1.14.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}