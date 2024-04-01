package dev.petuska.sponk

import dev.petuska.sponk.data.event.*
import dev.petuska.sponk.data.group.*
import dev.petuska.sponk.data.group.Group
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

@OptIn(ExperimentalStdlibApi::class)
interface Sponk : AutoCloseable {
  /**
   * Get details of all group memberships and all members of those groups.
   */
  fun listGroups(): Flow<Group>

  /**
   * Create a new group.
   *
   * @param newGroup the details of the group to be created.
   */
  suspend fun createGroup(newGroup: NewGroup): Group

  /**
   * Get existing group.
   *
   * @param id the [Group.id] of the group to fetch.
   */
  suspend fun getGroup(id: GroupId): Group

  /**
   * Delete a group.
   *
   * @param id the [Group.id] of the group to be deleted
   */
  suspend fun deleteGroup(id: GroupId)

  /**
   * Create a new group.
   *
   * @param groupId the id of parent group for this subgroup.
   * @param newSubGroup the details of the subgroup to be created.
   */
  suspend fun createSubGroup(groupId: GroupId, newSubGroup: NewSubGroup): SubGroup

  /**
   * Get events.
   * Subject to authenticated user's access.
   *
   * @param groupId only include events in this group.
   * @param subGroupId only include events in this subgroup.
   * @param includeScheduled include scheduled events.
   * @param includeHidden include hidden/cancelled events.
   * @param includeComments return event comments.
   * @param addProfileInfo return full profileInfos.
   * @param minStart only include events which start after or at this datetime.
   * @param maxStart only include events which start before or at this datetime.
   * @param minEnd only include events which end after or at this datetime.
   * @param maxEnd only include events which end before or at this datetime.
   * @param descending should the returned events be sorted from latest to earliest
   * @param limit maximum number of events to return
   */
  @Suppress("LongParameterList")
  fun listEvents(
    groupId: String? = null,
    subGroupId: String? = null,
    includeScheduled: Boolean = false,
    includeHidden: Boolean = false,
    includeComments: Boolean = false,
    addProfileInfo: Boolean = false,
    minStart: Instant? = null,
    maxStart: Instant? = null,
    minEnd: Instant? = null,
    maxEnd: Instant? = null,
    descending: Boolean = false,
    limit: UInt = 100u,
  ): Flow<Event>

  /**
   * Create a new event.
   *
   * @param newEvent the details of the event to be created.
   */
  suspend fun createEvent(newEvent: NewEvent): Event

  /**
   * Get existing event.
   *
   * @param id the [Event.id] of the event to fetch.
   * @param includeComments should comment data be included in the response
   * @param addProfileInfo should profile info data be included in the response
   */
  suspend fun getEvent(id: EventId, includeComments: Boolean = false, addProfileInfo: Boolean = false): Event

  /**
   * Update the existing event
   *
   * @param updatedEvent new event state
   */
  suspend fun updateEvent(updatedEvent: Event): Event

  /**
   * Cancel existing event.
   *
   * @param id the [Event.id] of the event to cancel.
   * @param quiet should the attendees be notified.
   * @param reason cancellation reason to show the attendees.
   */
  suspend fun cancelEvent(id: EventId, quiet: Boolean = false, reason: String? = null)

  /**
   * Autocompletes location candidates from a given [search] term.
   *
   * @param search term to lookup.
   */
  suspend fun autocompleteLocation(search: String): Flow<AutocompleteLocation>

  /**
   * Get location.
   * @param id the id of the location to fetch.
   */
  suspend fun getLocation(id: LocationId): Location

  companion object {
    /**
     * Build a [Sponk] instance from a preconfigured [HttpClient].
     * It is recommended to use [Sponk.login] builder for default configuration.
     * At the bare minimum, the client is expected to have default request
     * configured with base API URL & [ContentType.Application.Json] header.
     * Additionally, [Auth] plugin must be installed and configured for Bearer authentication.
     */
    operator fun invoke(json: Json, client: HttpClient): Sponk = DefaultSponk(json, client)

    /**
     * @param username typically the email used to login.
     * @param password user's password.
     * @param apiUrl spond API url, default will be used if left null.
     * @param onLoadTokens cached [BearerTokens] provider
     * @param onRefreshTokens callback invoked each time tokens are refreshed
     */
    fun login(
      username: String,
      password: String,
      apiUrl: String? = null,
      onLoadTokens: suspend () -> BearerTokens? = { null },
      onRefreshTokens: suspend (BearerTokens) -> Unit = {}
    ): Sponk {
      val url = apiUrl?.removeSuffix("/") ?: "https://api.spond.com/core/v1"
      val json = Json {
        ignoreUnknownKeys = true
      }
      val client = HttpClient {
        expectSuccess = true
        install(DefaultRequest) {
          contentType(ContentType.Application.Json)
          url("$url/")
        }
        install(ContentNegotiation) {
          json(json)
        }
        install(Auth) {
          bearer {
            loadTokens { onLoadTokens() }
            refreshTokens {
              println("Refreshing tokens")
              val response = client.post("$url/login") {
                contentType(ContentType.Application.Json)
                setBody(
                  mapOf(
                    "email" to username,
                    "password" to password,
                  )
                )
              }.body<JsonObject>()
              println("Refreshed tokens: $response")
              val accessToken =
                response["loginToken"]?.jsonPrimitive?.content ?: error("API did not return the Access Token")
              val refreshToken =
                response["passwordToken"]?.jsonPrimitive?.content ?: error("API did not return the Refresh Token")
              BearerTokens(accessToken = accessToken, refreshToken = refreshToken).also { onRefreshTokens(it) }
            }
          }
        }
      }
      return Sponk(json, client)
    }
  }
}
