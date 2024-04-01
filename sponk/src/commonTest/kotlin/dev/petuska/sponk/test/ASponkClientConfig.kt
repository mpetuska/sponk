package dev.petuska.sponk.test

import dev.petuska.sponk.data.event.EventId
import dev.petuska.sponk.data.group.GroupId
import io.ktor.client.plugins.auth.providers.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class ASponkClientConfig(
  val username: String,
  val password: String,
  val apiUrl: String? = null,
  val state: State = State()
) {
  @Serializable
  data class State(
    @Serializable(with = BearerTokensSerializer::class)
    val bearerTokens: BearerTokens? = null,
    val groupId: GroupId? = null,
    val eventId: EventId? = null,
  )
}

object BearerTokensSerializer : KSerializer<BearerTokens> {
  private val delegate = JsonObject.serializer()
  override val descriptor: SerialDescriptor = buildClassSerialDescriptor("BearerTokens", delegate.descriptor)

  override fun serialize(encoder: Encoder, value: BearerTokens) {
    encoder.encodeSerializableValue(
      serializer = delegate,
      value = JsonObject(
        mapOf(
          "accessToken" to JsonPrimitive(value.accessToken),
          "refreshToken" to JsonPrimitive(value.refreshToken),
        )
      )
    )
  }

  override fun deserialize(decoder: Decoder): BearerTokens {
    val json = decoder.decodeSerializableValue(delegate)
    val accessToken = requireNotNull(json["accessToken"]?.jsonPrimitive?.content)
    val refreshToken = requireNotNull(json["refreshToken"]?.jsonPrimitive?.content)
    return BearerTokens(accessToken, refreshToken)
  }
}
