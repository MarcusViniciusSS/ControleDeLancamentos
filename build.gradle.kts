val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

val postgres_version: String by project
val h2_version: String by project
val koin_ktor: String by project

plugins {
    kotlin("jvm") version "1.9.10"
    id("io.ktor.plugin") version "2.3.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
}

group = "org.marcusvinicus"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:2.3.5")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.5")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.5")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("io.ktor:ktor-server-cors-jvm:2.3.5")
    implementation("io.ktor:ktor-server-swagger-jvm:2.3.5")
    implementation("io.ktor:ktor-server-compression-jvm:2.3.5")
    implementation("io.ktor:ktor-server-forwarded-header-jvm:2.3.5")
    implementation("io.ktor:ktor-server-default-headers-jvm:2.3.5")
    implementation("org.postgresql:postgresql:$postgres_version")
    implementation("com.h2database:h2:$h2_version")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.5")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:2.3.5")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // Koin for Ktor
    implementation ("io.insert-koin:koin-ktor:$koin_ktor")
    // SLF4J Logger
    implementation ("io.insert-koin:koin-logger-slf4j:$koin_ktor")

    implementation("io.ktor:ktor-server-request-validation:$ktor_version")

    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
}
