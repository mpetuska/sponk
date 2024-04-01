package dev.petuska.sponk.data.group

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import dev.petuska.sponk.data.event.Group as EventGroup

@Serializable
data class Group(
  @SerialName("activity")
  var activity: String,
  @SerialName("addressFormat")
  var addressFormat: List<String>,
  @SerialName("adminsCanAddMembers")
  var adminsCanAddMembers: Boolean,
  @SerialName("allowPrivatePayoutAccounts")
  var allowPrivatePayoutAccounts: Boolean,
  @SerialName("allowSmsNag")
  var allowSmsNag: Boolean,
  @SerialName("bonusEnabled")
  var bonusEnabled: Boolean,
  @SerialName("contactInfoHidden")
  var contactInfoHidden: Boolean,
  @SerialName("contactPerson")
  var contactPerson: ContactPerson,
  @SerialName("countryCode")
  var countryCode: String,
  @SerialName("createdTime")
  var createdTime: Instant,
  @SerialName("defaultFields")
  var defaultFields: DefaultFields,
  @SerialName("eventVisibility")
  var eventVisibility: String,
  @SerialName("experiments")
  var experiments: Experiments? = null,
  @SerialName("fieldDefs")
  var fieldDefs: List<FieldDef>,
  @SerialName("guardianPermissions")
  var guardianPermissions: List<String>,
  @SerialName("id")
  var id: GroupId,
  @SerialName("imageUrl")
  var imageUrl: String? = null,
  @SerialName("invitedToAppTime")
  var invitedToAppTime: Instant,
  @SerialName("memberPermissions")
  var memberPermissions: List<String>,
  @SerialName("members")
  var members: List<Member>,
  @SerialName("membershipRequests")
  var membershipRequests: List<JsonElement>,
  @SerialName("name")
  var name: String,
  @SerialName("roles")
  var roles: List<Role>,
  @SerialName("shareContactInfo")
  var shareContactInfo: Boolean,
  @SerialName("signupUrl")
  var signupUrl: String? = null,
  @SerialName("subGroups")
  var subGroups: List<SubGroup>,
  @SerialName("type")
  var type: GroupType,
  @SerialName("welcomeMessage")
  var welcomeMessage: String? = null,
  @SerialName("chatAgeLimit")
  var chatAgeLimit: Int? = null,
  @SerialName("payoutAccounts")
  var payoutAccounts: List<JsonElement>? = null,
) {
  fun toEventGroup(): EventGroup = EventGroup(
    activity = activity,
    contactPersonId = contactPerson.id,
    createdTime = createdTime.epochSeconds,
    id = id,
    imageUrl = imageUrl,
    members = members.map(Member::toEventMember),
    name = name,
    subGroups = subGroups,
    type = type,
  )
}
