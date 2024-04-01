package dev.petuska.sponk.data.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Recipients(
  @SerialName("group")
  var group: Group,
  @SerialName("guardians")
  var guardians: List<JsonElement> = listOf(),
  @SerialName("groupMembers")
  var groupMembers: List<JsonElement> = listOf(),
  @SerialName("profiles")
  var profiles: List<JsonElement> = listOf()
)
