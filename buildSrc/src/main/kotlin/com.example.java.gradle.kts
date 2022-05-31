import org.gradle.kotlin.dsl.repositories
import kotlin.text.Charsets.UTF_8

plugins {
    id("java")
    id("io.quarkus") 
}

repositories {
    mavenCentral()
}

tasks.compileJava {
    if (JavaVersion.current() != JavaVersion.VERSION_17) {
        throw GradleException("Project requires to be built with Java 17")
    }
    options.encoding = UTF_8.toString()
    dependsOn("spotlessApply")
}

tasks.compileTestJava {
    options.encoding = UTF_8.toString()
    dependsOn("spotlessApply")
}

tasks.test {
    maxParallelForks = 5
}

java {
    toolchain{
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}