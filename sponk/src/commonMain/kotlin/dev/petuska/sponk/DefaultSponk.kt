package dev.petuska.sponk

import dev.petuska.sponk.data.event.*
import dev.petuska.sponk.data.group.*
import dev.petuska.sponk.data.group.Group
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

@Suppress("TooManyFunctions")
internal class DefaultSponk(
  private val json: Json,
  private val client: HttpClient,
) : Sponk {
  override fun close() {
    client.close()
  }

  override fun listGroups(): Flow<Group> = paginate {
    url("groups")
  }

  override suspend fun createGroup(newGroup: NewGroup): Group = client.post("group") {
    setBody(newGroup)
  }.body()

  override suspend fun getGroup(id: GroupId): Group = client.get("group/$id").body()

  override suspend fun deleteGroup(id: GroupId) {
    client.delete("group/$id")
  }

  override suspend fun createSubGroup(groupId: GroupId, newSubGroup: NewSubGroup): SubGroup =
    client.post("group/$groupId/subgroups") {
      setBody(newSubGroup)
    }.body()

  override fun listEvents(
    groupId: String?,
    subGroupId: String?,
    includeScheduled: Boolean,
    includeHidden: Boolean,
    includeComments: Boolean,
    addProfileInfo: Boolean,
    minStart: Instant?,
    maxStart: Instant?,
    minEnd: Instant?,
    maxEnd: Instant?,
    descending: Boolean,
    limit: UInt,
  ): Flow<Event> = paginate {
    url("sponds")
    parameter("groupId", groupId)
    parameter("subGroupId", subGroupId)
    parameter("scheduled", includeScheduled)
    parameter("includeHidden", includeHidden)
    parameter("includeComments", includeComments)
    parameter("addProfileInfo", addProfileInfo)
    parameter("minStartTimestamp", minStart)
    parameter("maxStartTimestamp", maxStart)
    parameter("minEndTimestamp", minEnd)
    parameter("maxEndTimestamp", maxEnd)
    parameter("order", if (descending) "desc" else "asc")
    parameter("max", limit)
  }

  override suspend fun createEvent(newEvent: NewEvent): Event = client.post("sponds") {
    setBody(newEvent)
  }.body()

  override suspend fun getEvent(id: EventId, includeComments: Boolean, addProfileInfo: Boolean): Event =
    client.get("sponds/$id") {
      parameter("includeComments", includeComments)
      parameter("addProfileInfo", addProfileInfo)
    }.body()

  override suspend fun updateEvent(updatedEvent: Event): Event = client.post("sponds/${updatedEvent.id}") {
    setBody(updatedEvent)
  }.body()

  override suspend fun cancelEvent(id: String, quiet: Boolean, reason: String?) {
    client.delete("sponds/$id") {
      parameter("quiet", quiet)
      reason?.let { parameter("reason", it) }
    }
  }

  override suspend fun autocompleteLocation(search: String): Flow<AutocompleteLocation> = flow {
    emitItems(
      client.get {
        url("locations/autocomplete")
        parameter("keyword", search)
        parameter("sessionToken", null)
      }
    )
  }

  override suspend fun getLocation(id: LocationId): Location = client.get {
    url("location/$id")
  }.body()

  private suspend inline fun <reified T> FlowCollector<T>.emitItems(response: HttpResponse) {
    emitItems(json, serializer<T>(), response)
  }

  private inline fun <reified T> paginate(
    crossinline builder: HttpRequestBuilder.() -> Unit,
  ): Flow<T> = flow<T> {
    var page = 0
    while (true) {
      val totalPages = client.prepareGet {
        builder()
        parameter("page", ++page)
      }.execute {
        emitItems(it)
        it.headers["x-wp-totalpages"]?.toInt() ?: 0
      }
      if (page >= totalPages) break
    }
  }.flowOn(Dispatchers.IO)
}

internal expect val Dispatchers.IO: CoroutineDispatcher

internal expect suspend fun <T> FlowCollector<T>.emitItems(
  json: Json,
  deserializer: KSerializer<T>,
  response: HttpResponse
)
