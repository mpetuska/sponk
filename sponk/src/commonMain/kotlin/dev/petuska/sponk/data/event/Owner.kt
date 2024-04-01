package dev.petuska.sponk.data.event

import dev.petuska.sponk.data.group.ProfileId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Owner(
  @SerialName("id")
  var id: ProfileId,
  @SerialName("response")
  var response: String? = null,
  @SerialName("email")
  var email: String? = null,
  @SerialName("firstName")
  var firstName: String? = null,
  @SerialName("lastName")
  var lastName: String? = null,
  @SerialName("appUser")
  var appUser: Boolean? = null,
  @SerialName("unableToReach")
  var unableToReach: Boolean? = null,
)
