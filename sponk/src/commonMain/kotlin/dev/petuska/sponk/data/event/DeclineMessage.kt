package dev.petuska.sponk.data.event

import dev.petuska.sponk.data.group.ProfileId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeclineMessage(
  @SerialName("message")
  var message: String,
  @SerialName("profileId")
  var profileId: ProfileId,
)
