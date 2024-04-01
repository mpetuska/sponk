package dev.petuska.sponk.data.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubGroup(
  @SerialName("color")
  var color: String? = null,
  @SerialName("id")
  var id: GroupId,
  @SerialName("name")
  var name: String? = null,
)
