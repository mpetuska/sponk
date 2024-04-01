plugins {
  id("kmp")
  id("detekt")
  id("local.properties")
}

kotlin {
  jvmToolchain(21)
  jvm {
    withJava()
  }
  linuxX64()

  sourceSets {
    commonMain {
      dependencies {
        api(libs.ktor.client.core)
        api(libs.kotlinx.datetime)
        api(libs.kotlinx.coroutines.core)
        api(libs.kotlinx.serialization.json)
        implementation(libs.ktor.client.auth)
        implementation(libs.ktor.client.content.negotiation)
        implementation(libs.ktor.serialization.kotlinx.json)
      }
    }
    commonTest {
      dependencies {
        implementation(libs.kotlinx.coroutines.test)
      }
    }
    jvmMain {
      dependencies {
        implementation(libs.ktor.client.cio)
      }
    }
    nativeMain {
      dependencies {
        implementation(libs.ktor.client.cio)
        implementation(libs.okio)
      }
    }
  }
}

tasks {
  withType<Test> {
    val configFileProp = providers.environmentVariable("SPONK_CONFIG_FILE")
      .orElse(providers.systemProperty("sponk.config.file"))
      .orElse(provider { ext.get("sponk.config.file")?.toString() })
      .orElse(providers.gradleProperty("sponk.config.file"))
    val configFilePath = configFileProp.get()
    val configFile = projectDir.resolve(configFilePath).takeIf(File::exists)
      ?: rootDir.resolve(configFilePath)
    inputs.file(configFile)
    environment(
      "SPONK_CONFIG_FILE",
      configFile.takeIf(File::exists)?.absolutePath ?: error("Sponk config file does not exist: $configFilePath")
    )
  }
}
