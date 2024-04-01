package dev.petuska.sponk.data.group

import dev.petuska.sponk.data.event.MemberId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewSubGroup(
  @SerialName("color")
  var color: String,
  @SerialName("name")
  var name: String,
  @SerialName("members")
  var members: List<MemberId>
)
