package dev.petuska.sponk.data.event

import dev.petuska.sponk.data.group.ProfileId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
  @SerialName("id")
  var id: ProfileId,
  @SerialName("unableToReach")
  var unableToReach: Boolean,
  @SerialName("contactMethod")
  var contactMethod: String? = null,
  @SerialName("email")
  var email: String? = null,
  @SerialName("firstName")
  var firstName: String? = null,
  @SerialName("lastName")
  var lastName: String? = null,
)
