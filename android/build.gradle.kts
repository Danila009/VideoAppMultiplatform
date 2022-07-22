plugins {
    id("org.jetbrains.compose") version "1.1.0"
    id("com.android.application")
    kotlin("android")
}

group = "me.danbe"
version = "1.0"

repositories {
    jcenter()
}

dependencies {
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.5.0")

    // Navigation
    implementation("io.github.alexgladkov:odyssey-core:1.0.0-beta12")
    implementation("io.github.alexgladkov:odyssey-compose:1.0.0-beta12")

//    //ExoPlayer
////    implementation("com.github.Danila009:exo-player-compose:0.2.0")
//    implementation("com.google.android.exoplayer:exoplayer:2.18.1")
//
//    //Glide
//    implementation("com.github.skydoves:landscapist-glide:1.5.3")

//    //Kodein
//    implementation("org.kodein.di:kodein-di-framework-android-core:7.14.0")
//    implementation("org.kodein.di:kodein-di-framework-android-x:7.14.0")
//    implementation("org.kodein.di:kodein-di-framework-compose:7.14.0")

    //Ktor
    implementation("io.ktor:ktor-client-core:2.0.2")
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "me.danbe.android"
        minSdkVersion(24)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}