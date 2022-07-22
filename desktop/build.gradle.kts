import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.1.0"
}

group = "me.danbe"
version = "1.0"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(compose.desktop.currentOs)

                // Navigation
                implementation("io.github.alexgladkov:odyssey-core:1.0.0-beta12")
                implementation("io.github.alexgladkov:odyssey-compose:1.0.0-beta12")

                //Kodein
                implementation("org.kodein.di:kodein-di-framework-android-core:7.14.0")
                implementation("org.kodein.di:kodein-di-framework-android-x:7.14.0")
                implementation("org.kodein.di:kodein-di-framework-compose:7.14.0")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "jvm"
            packageVersion = "1.0.0"
        }
    }
}