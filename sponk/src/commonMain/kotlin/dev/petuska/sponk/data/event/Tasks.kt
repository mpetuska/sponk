package dev.petuska.sponk.data.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Tasks(
  @SerialName("assignedTasks")
  var assignedTasks: List<JsonElement> = listOf(),
  @SerialName("openTasks")
  var openTasks: List<OpenTask> = listOf()
)
