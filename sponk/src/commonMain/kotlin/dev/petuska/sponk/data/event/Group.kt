package dev.petuska.sponk.data.event

import dev.petuska.sponk.data.group.GroupId
import dev.petuska.sponk.data.group.GroupType
import dev.petuska.sponk.data.group.ProfileId
import dev.petuska.sponk.data.group.SubGroup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Group(
  @SerialName("activity")
  var activity: String? = null,
  @SerialName("contactPersonId")
  var contactPersonId: ProfileId? = null,
  @SerialName("createdTime")
  var createdTime: Long? = null,
  @SerialName("id")
  var id: GroupId,
  @SerialName("imageUrl")
  var imageUrl: String? = null,
  @SerialName("members")
  var members: List<Member> = listOf(),
  @SerialName("name")
  var name: String? = null,
  @SerialName("subGroups")
  var subGroups: List<SubGroup> = listOf(),
  @SerialName("type")
  var type: GroupType? = null
)
