package dev.petuska.sponk.data.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Role(
  @SerialName("id")
  var id: String,
  @SerialName("name")
  var name: String,
  @SerialName("permissions")
  var permissions: List<String>
)
