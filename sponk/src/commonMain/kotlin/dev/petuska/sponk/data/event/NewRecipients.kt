package dev.petuska.sponk.data.event

import dev.petuska.sponk.data.group.GroupId
import dev.petuska.sponk.data.group.ProfileId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class NewRecipients(
  @SerialName("group")
  var group: Group,
  @SerialName("guardians")
  var guardians: List<JsonElement> = listOf(),
  @SerialName("groupMembers")
  var groupMembers: List<JsonElement> = listOf(),
  @SerialName("profiles")
  var profiles: List<JsonElement> = listOf()
) {
  @Serializable
  data class Group(
    @SerialName("id")
    var id: GroupId,
    @SerialName("subGroups")
    var subGroups: List<GroupId>,
  )
}
