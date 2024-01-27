package plugin

import AppConfig
import baseDependencies
import composeDependencies
import org.gradle.kotlin.dsl.kotlin
import testDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}
android {
    compileSdk = AppConfig.compileSdk
    defaultConfig {
        minSdk = AppConfig.minSdk
        lint.targetSdk = AppConfig.targetSdk
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        testBuildType = "debug"
        debug { }
        release { }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.KotlinCompilerExtension
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    libraryVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir(File("build/generated/ksp/$name/kotlin"))
            }
        }
    }
}
kapt {
    correctErrorTypes = true
}
dependencies {
    baseDependencies()
    composeDependencies()
    testDependencies()
}
