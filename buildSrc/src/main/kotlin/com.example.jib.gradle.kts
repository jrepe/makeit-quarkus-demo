import java.io.ByteArrayOutputStream

plugins {
    id("com.google.cloud.tools.jib")
}

fun getImageVersion(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine = listOf("git", "rev-parse", "--short=12", "HEAD")
        standardOutput = stdout
    }
    return "${stdout.toString().trim()}-${System.currentTimeMillis()}"
}

jib {
    from {
        image = "gcr.io/distroless/java17-debian11"
    }
    to {
        image = "eu.gcr.io/jurer-playground/makeit/makeit-quarkus-demo"
        tags = setOf(getImageVersion())
    }
    container {
        mainClass = "anything"
        jvmFlags = listOf("-Dquarkus.http.host=0.0.0.0", "-Djava.util.logging.manager=org.jboss.logmanager.LogManager", "-Xmx256m")
        ports = listOf("8080")
    }
    pluginExtensions {
        pluginExtension {
            implementation = "com.google.cloud.tools.jib.gradle.extension.quarkus.JibQuarkusExtension"
        }
    }
}

val outJibImage = tasks.register("outJibImage") {
    dependsOn("jib")
    doLast {
        file("${project.buildDir}/image-name.txt").writeText("${jib.to.image}:${jib.to.tags.toList().first()}")
    }
}

tasks.named("jibBuildTar") {
    dependsOn("quarkusBuild")
}

tasks.named("jibDockerBuild") {
    dependsOn("quarkusBuild")
}

tasks.named("jib") {
    dependsOn("quarkusBuild")
    finalizedBy(outJibImage)
}