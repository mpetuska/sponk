package dev.petuska.sponk

import io.ktor.client.statement.*
import io.ktor.utils.io.jvm.javaio.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.DecodeSequenceMode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeToSequence

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
internal actual inline val Dispatchers.IO: CoroutineDispatcher
  get() = IO

@OptIn(ExperimentalSerializationApi::class)
internal actual suspend fun <T> FlowCollector<T>.emitItems(
  json: Json,
  deserializer: KSerializer<T>,
  response: HttpResponse
) {
  val stream = response.bodyAsChannel().toInputStream()
  json.decodeToSequence(
    stream = stream,
    deserializer = deserializer,
    format = DecodeSequenceMode.ARRAY_WRAPPED
  ).forEach { emit(it) }.also { stream.close() }
}
