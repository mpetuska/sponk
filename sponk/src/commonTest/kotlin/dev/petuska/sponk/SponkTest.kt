package dev.petuska.sponk

import dev.petuska.sponk.data.event.NewEvent
import dev.petuska.sponk.data.event.NewRecipients
import dev.petuska.sponk.data.event.Recipients
import dev.petuska.sponk.data.group.GroupType
import dev.petuska.sponk.data.group.NewGroup
import dev.petuska.sponk.data.group.NewSubGroup
import dev.petuska.sponk.test.ASponkImplClientTest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.days

class SponkTest : ASponkImplClientTest() {
  @Test
  fun listGroups() = runTest {
    val groups = sponk.listGroups().first()
    assertNotNull(groups)
  }

  @Test
  fun createGroup() = runTest {
    val group = sponk.createGroup(
      NewGroup(
        activity = "volleyball",
        type = GroupType.Mixed,
        name = "Mixed Test Volleyball",
        welcomeMessage = "A mixed test group to try out spond features.",
        subGroups = listOf(
          NewSubGroup(
            color = "#000000",
            name = "Main",
            members = listOf(),
          ),
          NewSubGroup(
            color = "#FFFFFF",
            name = "Side",
            members = listOf(),
          )
        )
      )
    ).also { updateState(state.copy(groupId = it.id)) }
    assertNotNull(group)
  }

  @Test
  fun getGroup() = runTest {
    val group = sponk.getGroup(state.groupId ?: error("No groupId specified"))
    assertNotNull(group)
  }

  @Test
  fun deleteGroup() = runTest {
    val result = sponk.deleteGroup(state.groupId ?: error("No groupId specified"))
      .also { updateState(state.copy(groupId = null, eventId = null)) }
    assertNotNull(result)
  }

  @Test
  fun deleteAllGroups() = runTest {
    val result = sponk.listGroups().count {
      supervisorScope {
        sponk.deleteGroup(it.id)
      }
      true
    }
    updateState(state.copy(groupId = null, eventId = null))
    assertTrue(result > 0)
  }

  @Test
  fun listEvents() = runTest {
    val events = sponk.listEvents(
      groupId = state.groupId,
      includeScheduled = true,
      includeComments = true,
      includeHidden = true,
      addProfileInfo = true,
      limit = 10u
    ).toList()
    assertNotNull(events)
  }

  @Test
  fun createEvent() = runTest {
    val group = sponk.getGroup(state.groupId ?: error("No groupId specified"))
    val event = sponk.createEvent(
      NewEvent(
        heading = "Test Event OK",
        startTimestamp = Clock.System.now().plus(4.days),
        meetupPrior = "5",
        maxAccepted = 0,
        recipients = NewRecipients(
          group = NewRecipients.Group(
            id = group.id,
            subGroups = listOfNotNull(state.subGroupId)
          ),
        )
      )
    )
      .also { updateState(state.copy(eventId = it.id)) }
    assertNotNull(event)
  }

  @Test
  fun updateEvent() = runTest {
    val event = sponk.getEvent(state.eventId ?: "No eventId specified")
    val updatedEvent = sponk.updateEvent(event.apply {
      heading = "My Event Updated"
    })
    assertNotNull(updatedEvent)
  }

  @Test
  fun getEvent() = runTest {
    val event =
      sponk.getEvent(state.eventId ?: error("No eventId specified"), includeComments = true, addProfileInfo = true)
    assertNotNull(event)
  }

  @Test
  fun cancelEvent() = runTest {
    val result =
      sponk.cancelEvent(state.eventId ?: error("No eventId specified"), quiet = true, reason = "Because I said so!")
        .also { updateState(state.copy(eventId = null)) }
    assertNotNull(result)
  }

  @Test
  fun deleteAllEvents() = runTest {
    val result = sponk.listEvents(
      groupId = state.groupId ?: "No groupId specified",
      includeScheduled = true,
      includeHidden = true,
      limit = 999u
    ).count {
      supervisorScope {
        it.id?.let { id -> sponk.cancelEvent(id, quiet = true, reason = "Cleanup") }
      }
      true
    }
    updateState(state.copy(eventId = null))
    assertTrue(result > 0)
  }
}
