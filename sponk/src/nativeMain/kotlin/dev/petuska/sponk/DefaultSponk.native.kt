package dev.petuska.sponk

import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
internal actual inline val Dispatchers.IO: CoroutineDispatcher
  get() = IO

internal actual suspend fun <T> FlowCollector<T>.emitItems(
  json: Json,
  deserializer: KSerializer<T>,
  response: HttpResponse
) {
  json.decodeFromString(ListSerializer(deserializer), response.bodyAsText()).forEach { emit(it) }
}
