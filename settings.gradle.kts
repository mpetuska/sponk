pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
  }
  includeBuild("./build-conventions")
}

plugins {
  id("settings")
}

rootProject.name = "Sponk"
include(
  ":sponk"
)
