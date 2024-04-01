package dev.petuska.sponk.data.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactPerson(
  @SerialName("contactMethod")
  var contactMethod: String,
  @SerialName("email")
  var email: String,
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
)
