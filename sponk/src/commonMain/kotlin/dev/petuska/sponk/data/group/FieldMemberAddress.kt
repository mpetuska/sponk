package dev.petuska.sponk.data.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FieldMemberAddress(
  @SerialName("locked")
  var locked: Boolean,
  @SerialName("permission")
  var permission: String,
  @SerialName("required")
  var required: Boolean
)
