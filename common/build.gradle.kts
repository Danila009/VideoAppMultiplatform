import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.6.10"
    id("org.jetbrains.compose") version "1.1.0"
    id("com.android.library")
}

group = "me.danbe"
version = "1.0"

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)

                val mokoMvvmVersion = "0.13.0"
                api("dev.icerock.moko:mvvm-core:$mokoMvvmVersion")
                api("dev.icerock.moko:mvvm-flow:$mokoMvvmVersion")

                // DI
                implementation("org.kodein.di:kodein-di:7.14.0")
                implementation("org.kodein.di:kodein-di-framework-compose:7.14.0")

                //Ktor
                implementation("io.ktor:ktor-client-core:2.0.2")
//                implementation("io.ktor:ktor-client-logging:2.0.2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.2")
                implementation("io.ktor:ktor-client-content-negotiation:2.0.2")
//                implementation("io.ktor:ktor-client-apache:2.0.2")

                // Navigation
                implementation("io.github.alexgladkov:odyssey-core:1.0.0-beta12")
                implementation("io.github.alexgladkov:odyssey-compose:1.0.0-beta12")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.4.2")
                api("androidx.core:core-ktx:1.8.0")

                //Ktor
                implementation("io.ktor:ktor-client-core:2.0.2")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)

                //Ktor
                implementation("io.ktor:ktor-client-core:2.0.2")
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(31)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}