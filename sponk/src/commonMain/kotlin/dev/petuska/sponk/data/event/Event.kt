package dev.petuska.sponk.data.event

import dev.petuska.sponk.data.group.ProfileId
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Event(
  @SerialName("attachments")
  var attachments: List<JsonElement> = listOf(),
  @SerialName("autoAccept")
  var autoAccept: Boolean,
  @SerialName("autoReminderTime")
  var autoReminderTime: String? = null,
  @SerialName("autoReminderType")
  var autoReminderType: String? = null,
  @SerialName("behalfOfIds")
  var behalfOfIds: List<String> = listOf(),
  @SerialName("comments")
  var comments: List<JsonElement> = listOf(),
  @SerialName("commentsDisabled")
  var commentsDisabled: Boolean,
  @SerialName("createdTime")
  var createdTime: Instant? = null,
  @SerialName("creatorId")
  var creatorId: ProfileId? = null,
  @SerialName("description")
  var description: String? = null,
  @SerialName("endTimestamp")
  var endTimestamp: Instant,
  @SerialName("meetupTimestamp")
  var meetupTimestamp: Instant? = null,
  @SerialName("meetupPrior")
  var meetupPrior: Int? = null,
  @SerialName("expired")
  var expired: Boolean? = null,
  @SerialName("heading")
  var heading: String = "",
  @SerialName("maxAccepted")
  var maxAccepted: UInt? = null,
  @SerialName("inviteTime")
  var inviteTime: Instant? = null,
  @SerialName("rsvpDate")
  var rsvpDate: Instant? = null,
  @SerialName("hidden")
  var hidden: Boolean? = null,
  @SerialName("id")
  var id: EventId? = null,
  @SerialName("lastNagged")
  var lastNagged: String? = null,
  @SerialName("location")
  var location: Location? = null,
  @SerialName("matchEvent")
  var matchEvent: Boolean? = null,
  @SerialName("matchInfo")
  var matchInfo: MatchInfo? = null,
  @SerialName("modifiedFromSeries")
  var modifiedFromSeries: Boolean? = null,
  @SerialName("owners")
  var owners: List<Owner>,
  @SerialName("participantsHidden")
  var participantsHidden: Boolean,
  @SerialName("recipients")
  var recipients: Recipients,
  @SerialName("registered")
  var registered: Boolean? = null,
  @SerialName("responses")
  var responses: Responses = Responses(),
  @SerialName("seriesId")
  var seriesId: String? = null,
  @SerialName("seriesOrdinal")
  var seriesOrdinal: Int? = null,
  @SerialName("startTimestamp")
  var startTimestamp: Instant,
  @SerialName("tasks")
  var tasks: Tasks,
  @SerialName("type")
  var type: String,
  @SerialName("spondType")
  var spondType: String? = null,
  @SerialName("updated")
  var updated: Long? = null,
  @SerialName("visibility")
  var visibility: String,
  @SerialName("openEnded")
  var openEnded: Boolean? = null,
  @SerialName("cancelled")
  var cancelled: Boolean? = null,
  @SerialName("cancelledReason")
  var cancelledReason: String? = null,
)
