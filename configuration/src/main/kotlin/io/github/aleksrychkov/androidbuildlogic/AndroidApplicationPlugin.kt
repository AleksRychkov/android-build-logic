package io.github.aleksrychkov.androidbuildlogic

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

abstract class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
        }

        configure<ApplicationExtension> {
            namespace = projectApplicationId()
            compileSdk = projectCompileSdk()

            setDefaultConfig(project = target)
            setCompilationOptions(project = target)
        }
    }

    private fun ApplicationExtension.setDefaultConfig(project: Project) {
        defaultConfig {
            applicationId = project.projectApplicationId()
            minSdk = project.projectMinSdk()
            targetSdk = project.projectTargetSdk()
            versionCode = project.projectVersionCode()
            versionName = project.projectVersionName()

            testInstrumentationRunner = "androidx.test.runner.AndroidJUintRunner"
        }
    }

    private fun ApplicationExtension.setCompilationOptions(project: Project) {
        compileOptions {
            sourceCompatibility = JAVA_VERSION
            targetCompatibility = JAVA_VERSION
        }
        project.setKotlinCompileOptions()
    }
}
