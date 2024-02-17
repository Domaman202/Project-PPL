plugins {
    kotlin("jvm") version "1.9.22"
}

group = "ru.DmN.phtx"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("ru.DmN.pht:Project-Pihta:1.15.0")
    implementation("uk.co.caprica:vlcj:4.8.2")
    implementation("com.itextpdf:itextpdf:5.5.13.3")
    testImplementation(kotlin("test"))
    testImplementation("org.apache.commons:commons-lang3:3.14.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}