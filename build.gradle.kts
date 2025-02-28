plugins {
    id("java")
    id("java-base")
    id("java-library")
    id("maven-publish")
}


group = "de.timesnake"
version = "4.1.0"
var projectId = 45

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://git.timesnake.de/api/v4/groups/7/-/packages/maven")
        name = "timesnake"
        credentials(PasswordCredentials::class)
    }
}

dependencies {
    compileOnly("de.timesnake:channel-api:5.+")

    compileOnly("de.timesnake:library-basic:2.+")
    compileOnly("de.timesnake:library-chat:2.+")

    compileOnly("org.apache.logging.log4j:log4j-api:2.22.1")
    compileOnly("org.apache.logging.log4j:log4j-core:2.22.1")

    implementation("org.apache.commons:commons-dbcp2:2.9+")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.0.6")

    compileOnly("net.kyori:adventure-api:4.11.0")
}

configurations.configureEach {
    resolutionStrategy.dependencySubstitution {
        if (project.parent != null) {
            substitute(module("de.timesnake:channel-api")).using(project(":channel:channel-api"))

            substitute(module("de.timesnake:library-basic")).using(project(":libraries:library-basic"))
            substitute(module("de.timesnake:library-chat")).using(project(":libraries:library-chat"))
        }
    }
}

publishing {
    repositories {
        maven {
            url = uri("https://git.timesnake.de/api/v4/projects/$projectId/packages/maven")
            name = "timesnake"
            credentials(PasswordCredentials::class)
        }
    }

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release = 21
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    withSourcesJar()
}