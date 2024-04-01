package dev.petuska.sponk.data.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FieldDef(
  @SerialName("id")
  var id: String,
  @SerialName("locked")
  var locked: Boolean,
  @SerialName("name")
  var name: String,
  @SerialName("permission")
  var permission: String,
  @SerialName("required")
  var required: Boolean,
  @SerialName("source")
  var source: String,
  @SerialName("type")
  var type: String,
  @SerialName("visibility")
  var visibility: String
)
