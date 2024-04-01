package dev.petuska.sponk.test

import dev.petuska.sponk.IO
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKStringFromUtf8
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import okio.*
import okio.Path.Companion.toPath
import platform.posix.getenv

actual object ASponkClientConfigLoader {
  @OptIn(ExperimentalForeignApi::class)
  private val configFile: Path =
    getenv("SPONK_CONFIG_FILE")?.let(CPointer<ByteVar>::toKStringFromUtf8)?.toPath()
      ?: error("SPONK_CONFIG_FILE not specified")

  actual fun load(): ASponkClientConfig {
    println("Loading config from file: $configFile")
    val json = FileSystem.SYSTEM.read(configFile) {
      buildString {
        while (!exhausted()) {
          readUtf8Line()?.let(::appendLine)
        }
      }
    }
    return PrettyLenientJson.decodeFromString(json)
  }

  actual suspend fun save(config: ASponkClientConfig) {
    withContext(Dispatchers.IO) {
      FileSystem.SYSTEM.write(configFile) {
        writeUtf8(PrettyLenientJson.encodeToString(config))
      }
    }
  }
}
