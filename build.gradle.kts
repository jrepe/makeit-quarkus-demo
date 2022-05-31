plugins {
    id("com.example.java")
    id("com.example.spotless")
    id("com.example.jib")
}

group = "com.example"
version = "1.0.0-SNAPSHOT"
description = "Quarkus logging application demo for MakeIT conference"

val quarkusVersion = "2.9.1.Final"
val lombokVersion = "1.18.24"
dependencies {
    implementation(enforcedPlatform("io.quarkus:quarkus-bom:${quarkusVersion}"))
    implementation("io.quarkus:quarkus-resteasy-jackson")
    implementation("io.quarkus:quarkus-rest-client-jackson")
    implementation("io.quarkus:quarkus-container-image-jib")

    // Quarkiverse    
    implementation("io.quarkiverse.loggingjson:quarkus-logging-json:1.1.1")

    implementation("com.google.guava:guava:31.1-jre")

    // Lombok
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
}