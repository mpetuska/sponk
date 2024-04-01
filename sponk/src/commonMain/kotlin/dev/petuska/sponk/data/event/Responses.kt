package dev.petuska.sponk.data.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Responses(
  @SerialName("acceptedIds")
  var acceptedIds: List<MemberId> = listOf(),
  @SerialName("declineMessages")
  var declineMessages: Map<MemberId, DeclineMessage>? = null,
  @SerialName("declinedIds")
  var declinedIds: List<MemberId> = listOf(),
  @SerialName("unansweredIds")
  var unansweredIds: List<MemberId> = listOf(),
  @SerialName("unconfirmedIds")
  var unconfirmedIds: List<MemberId> = listOf(),
  @SerialName("waitinglistIds")
  var waitingListIds: List<MemberId> = listOf(),
)
