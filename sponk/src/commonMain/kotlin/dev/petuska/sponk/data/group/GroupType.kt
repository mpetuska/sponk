package dev.petuska.sponk.data.group

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = GroupType.Serializer::class)
enum class GroupType {
  Youth,
  Adults,
  Mixed;

  companion object Serializer : KSerializer<GroupType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("GroupType", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): GroupType = GroupType.entries[decoder.decodeInt()]

    override fun serialize(encoder: Encoder, value: GroupType) {
      encoder.encodeInt(value.ordinal)
    }
  }
}
