package dev.petuska.sponk.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.decodeFromStream
import java.io.File

actual object ASponkClientConfigLoader {
  private val configFile: File =
    System.getenv("SPONK_CONFIG_FILE")?.let(::File) ?: error("SPONK_CONFIG_FILE not specified")

  @OptIn(ExperimentalSerializationApi::class)
  actual fun load(): ASponkClientConfig {
    println("Loading config from file: $configFile")
    return configFile.inputStream().use(PrettyLenientJson::decodeFromStream)
  }

  actual suspend fun save(config: ASponkClientConfig) {
    withContext(Dispatchers.IO) {
      configFile.parentFile.mkdirs()
      configFile.writeText(PrettyLenientJson.encodeToString(config))
    }
  }
}
