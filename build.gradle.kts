plugins {
    kotlin("jvm") version "1.9.21"
    `maven-publish`
}

group = "ru.DmN.phtx"
version = "1.3.2"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("ru.DmN.pht:Project-Pihta:1.16.2")
    implementation("uk.co.caprica:vlcj:4.8.2")
    implementation("com.itextpdf:itextpdf:5.5.13.3")
    testImplementation(kotlin("test"))
    testImplementation("org.apache.commons:commons-lang3:3.14.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}


java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    val fatJar = register<Jar>("fatJar") {
        dependsOn.addAll(listOf("compileJava", "compileKotlin", "processResources"))
        archiveClassifier.set("standalone")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest { attributes(mapOf("Main-Class" to "ru.DmN.phtx.ppl.ConsoleImpl")) }
        val sourcesMain = sourceSets.main.get()
        val contents = configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) } + sourcesMain.output
        from(contents)
    }

    build {
        dependsOn(fatJar)
    }

    java {
        withSourcesJar()
        withJavadocJar()
    }

    test {
        useJUnitPlatform()
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group as String
            artifactId = "Project-PPL"
            version = project.version as String
            from(components["java"])
        }
    }
}