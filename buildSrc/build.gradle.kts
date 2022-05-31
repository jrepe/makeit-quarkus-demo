plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.5.1")
    implementation("io.quarkus:gradle-application-plugin:2.9.1.Final")
    implementation("gradle.plugin.com.google.cloud.tools:jib-gradle-plugin:3.2.1")
    implementation("com.google.cloud.tools:jib-quarkus-extension-gradle:0.1.1")
}