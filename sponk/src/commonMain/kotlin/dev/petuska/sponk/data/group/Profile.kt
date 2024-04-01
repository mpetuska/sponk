package dev.petuska.sponk.data.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import dev.petuska.sponk.data.event.Profile as EventProfile

@Serializable
data class Profile(
  @SerialName("contactMethod")
  var contactMethod: String,
  @SerialName("email")
  var email: String? = null,
  @SerialName("firstName")
  var firstName: String,
  @SerialName("id")
  var id: ProfileId,
  @SerialName("imageUrl")
  var imageUrl: String? = null,
  @SerialName("lastName")
  var lastName: String,
  @SerialName("phoneNumber")
  var phoneNumber: String? = null,
  @SerialName("unableToReach")
  var unableToReach: Boolean
) {
  fun toEventProfile(): EventProfile = EventProfile(
    id = id,
    unableToReach = unableToReach,
    contactMethod = contactMethod,
    email = email,
    firstName = firstName,
    lastName = lastName
  )
}
