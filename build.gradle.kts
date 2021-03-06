val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val koinVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
}

group = "com.bhardwaj"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

// Necessary For Deploying to Heroku.
tasks.create("stage") {
    dependsOn("installDist")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.insert-koin:koin-ktor:$koinVersion")
    implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")
    implementation("io.ktor:ktor-gson:1.6.7")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

//    Other Servers Supported by ktor
//    implementation("io.ktor:ktor-server-jetty:$ktor_version")
//    implementation("io.ktor:ktor-server-tomcat:$ktor_version")
//    implementation("io.ktor:ktor-server-cio:$ktor_version")
//    implementation("io.ktor:ktor-server-test-host:$ktor_version")

}