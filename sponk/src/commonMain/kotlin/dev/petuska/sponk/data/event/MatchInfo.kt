package dev.petuska.sponk.data.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchInfo(
  @SerialName("opponentColour")
  var opponentColour: String? = null,
  @SerialName("opponentName")
  var opponentName: String,
  @SerialName("scoresFinal")
  var scoresFinal: Boolean? = null,
  @SerialName("scoresPublic")
  var scoresPublic: Boolean? = null,
  @SerialName("scoresSet")
  var scoresSet: Boolean? = null,
  @SerialName("scoresSetEver")
  var scoresSetEver: Boolean? = null,
  @SerialName("teamColour")
  var teamColour: String? = null,
  @SerialName("teamName")
  var teamName: String,
  @SerialName("type")
  var type: MatchType
)
