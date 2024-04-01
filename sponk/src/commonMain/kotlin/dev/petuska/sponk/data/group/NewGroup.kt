package dev.petuska.sponk.data.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewGroup(
  @SerialName("activity")
  var activity: String,
  @SerialName("type")
  var type: GroupType,
  @SerialName("chatAgeLimit")
  var chatAgeLimit: Int = when (type) {
    GroupType.Mixed,
    GroupType.Youth -> 0

    GroupType.Adults -> 18
  },
  @SerialName("contactInfoHidden")
  var contactInfoHidden: Boolean = false,
  @SerialName("name")
  var name: String,
  @SerialName("primaryContact")
  var primaryContact: String = "email",
  @SerialName("subGroups")
  var subGroups: List<NewSubGroup> = listOf(),
  @SerialName("welcomeMessage")
  var welcomeMessage: String? = null
) {
  @Serializable
  data class SubGroup(
    @SerialName("color")
    var color: String,
    @SerialName("index")
    var index: UInt? = null,
    @SerialName("name")
    var name: String
  )
}
