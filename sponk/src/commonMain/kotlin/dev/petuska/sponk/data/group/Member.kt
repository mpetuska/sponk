package dev.petuska.sponk.data.group

import dev.petuska.sponk.data.event.MemberId
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import dev.petuska.sponk.data.event.Member as EventMember

@Serializable
data class Member(
  @SerialName("address")
  var address: List<String?>? = null,
  @SerialName("createdTime")
  var createdTime: Instant,
  @SerialName("dateOfBirth")
  var dateOfBirth: String,
  @SerialName("email")
  var email: String? = null,
  @SerialName("fields")
  var fields: Map<FieldId, JsonPrimitive>? = null,
  @SerialName("firstName")
  var firstName: String,
  @SerialName("guardians")
  var guardians: List<JsonElement>,
  @SerialName("id")
  var id: MemberId,
  @SerialName("lastName")
  var lastName: String,
  @SerialName("phoneNumber")
  var phoneNumber: String? = null,
  @SerialName("profile")
  var profile: Profile,
  @SerialName("respondent")
  var respondent: Boolean,
  @SerialName("roles")
  var roles: List<String>? = null,
  @SerialName("subGroups")
  var subGroups: List<String>,
  @SerialName("verifiedDateOfBirth")
  var verifiedDateOfBirth: Boolean? = null,
) {
  fun toEventMember(): EventMember = EventMember(
    firstName = firstName,
    guardians = guardians,
    id = id,
    lastName = lastName,
    profile = profile.toEventProfile(),
    respondent = respondent,
    email = email,
  )
}
