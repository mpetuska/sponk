package dev.petuska.sponk.test

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

expect object ASponkClientConfigLoader {
  fun load(): ASponkClientConfig
  suspend fun save(config: ASponkClientConfig)
}

@OptIn(ExperimentalSerializationApi::class)
internal val PrettyLenientJson = Json {
  prettyPrint = true
  prettyPrintIndent = "  "
  ignoreUnknownKeys = true
  encodeDefaults = true
}
