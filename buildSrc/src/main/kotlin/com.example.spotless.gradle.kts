import gradle.kotlin.dsl.accessors._01a7fddcf81aa279b379d6fe3cb64505.spotless

plugins {
    id("com.diffplug.spotless")
}

spotless {
    ratchetFrom("origin/master")
    java {
        palantirJavaFormat()
    }
}