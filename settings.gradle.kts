pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://www.jitpack.io")
        maven { url = uri("https://www.jitpack.io" ) }
    }
    
}
rootProject.name = "You_Tube"


include(":android")
include(":desktop")
include(":common")

