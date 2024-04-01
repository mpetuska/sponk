package dev.petuska.sponk.data.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Member(
  @SerialName("firstName")
  var firstName: String,
  @SerialName("guardians")
  var guardians: List<JsonElement>,
  @SerialName("id")
  var id: MemberId,
  @SerialName("lastName")
  var lastName: String,
  @SerialName("profile")
  var profile: Profile,
  @SerialName("respondent")
  var respondent: Boolean,
  @SerialName("email")
  var email: String? = null,
)
