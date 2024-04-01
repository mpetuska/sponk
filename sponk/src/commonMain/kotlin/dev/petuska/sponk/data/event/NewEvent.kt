package dev.petuska.sponk.data.event

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlin.time.Duration.Companion.hours

@Serializable
data class NewEvent(
  @SerialName("attachments")
  var attachments: List<JsonElement> = listOf(),
  @SerialName("autoAccept")
  var autoAccept: Boolean = false,
  @SerialName("autoReminderType")
  var autoReminderType: String = "DISABLED",
  @SerialName("commentsDisabled")
  var commentsDisabled: Boolean = false,
  @SerialName("description")
  var description: String? = null,
  @SerialName("heading")
  var heading: String = "",
  @SerialName("inviteTime")
  var inviteTime: Instant? = null,
  @SerialName("location")
  var location: Location? = null,
  @SerialName("matchInfo")
  var matchInfo: MatchInfo? = null,
  @SerialName("maxAccepted")
  var maxAccepted: Int? = null,
  @SerialName("meetupPrior")
  var meetupPrior: String? = null,
  @SerialName("openEnded")
  var openEnded: Boolean? = null,
  @SerialName("owners")
  var owners: List<Owner> = listOf(),
  @SerialName("participantsHidden")
  var participantsHidden: Boolean? = null,
  @SerialName("recipients")
  var recipients: NewRecipients? = null,
  @SerialName("rsvpDate")
  var rsvpDate: Instant? = null,
  @SerialName("spondType")
  var spondType: String = "event",
  @SerialName("startTimestamp")
  var startTimestamp: Instant,
  @SerialName("endTimestamp")
  var endTimestamp: Instant = startTimestamp + 1.hours,
  @SerialName("tasks")
  var tasks: Tasks = Tasks(),
  @SerialName("type")
  var type: String = "EVENT",
  @SerialName("visibility")
  var visibility: String = "INVITEES"
)
