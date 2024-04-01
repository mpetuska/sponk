package dev.petuska.sponk.data.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class OpenTask(
  @SerialName("accepted")
  var accepted: List<JsonElement> = listOf(),
  @SerialName("adultsOnly")
  var adultsOnly: Boolean,
  @SerialName("declined")
  var declined: List<JsonElement> = listOf(),
  @SerialName("description")
  var description: String,
  @SerialName("id")
  var id: String? = null,
  @SerialName("limit")
  var limit: Int,
  @SerialName("name")
  var name: String,
  @SerialName("remaining")
  var remaining: Int? = null,
  @SerialName("type")
  var type: String,
  @SerialName("unanswered")
  var unanswered: List<JsonElement> = listOf()
)
