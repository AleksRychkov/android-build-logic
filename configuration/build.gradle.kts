import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "io.github.aleksrychkov"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("build-logic-android-application") {
            id = "build-logic.application"
            implementationClass = "io.github.aleksrychkov.androidbuildlogic.AndroidApplicationPlugin"
        }
        register("build-logic-android-library") {
            id = "build-logic.android-library"
            implementationClass = "io.github.aleksrychkov.androidbuildlogic.AndroidLibraryPlugin"
        }
        register("build-logic-library") {
            id = "build-logic.library"
            implementationClass = "io.github.aleksrychkov.androidbuildlogic.LibraryPlugin"
        }
    }
}
