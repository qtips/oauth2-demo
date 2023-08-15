plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "no.qadeer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.glassfish.jersey.containers:jersey-container-jetty-http:3.1.3")
    implementation("org.glassfish.jersey.inject:jersey-hk2:3.1.3")
    implementation("jakarta.servlet:jakarta.servlet-api:5.0.0")
    implementation("org.glassfish.jersey.media:jersey-media-json-jackson:3.1.3")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("StartMyApp")
}