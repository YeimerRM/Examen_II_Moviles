package com.app.copamundialfifa2026

import com.app.copamundialfifa2026.data.model.Ticket
import com.app.copamundialfifa2026.data.model.TicketCategory
import com.app.copamundialfifa2026.data.model.TicketPriority
import com.app.copamundialfifa2026.data.model.TicketStatus
import com.app.copamundialfifa2026.data.model.sortedForDisplay
import com.app.copamundialfifa2026.data.remote.dto.CreateTicketRequest
import com.app.copamundialfifa2026.data.repository.TicketRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TicketRepositoryTest {
    @Test
    fun sortedForDisplay_ordersByPriorityThenDate() {
        val tickets = listOf(
            Ticket("1", "Low", "", TicketPriority.LOW, TicketStatus.OPEN, "A", TicketCategory.LOGISTICS, "2026-05-20"),
            Ticket("2", "High older", "", TicketPriority.HIGH, TicketStatus.OPEN, "B", TicketCategory.LOGISTICS, "2026-05-21"),
            Ticket("3", "Critical", "", TicketPriority.CRITICAL, TicketStatus.OPEN, "C", TicketCategory.LOGISTICS, "2026-05-19"),
            Ticket("4", "High newer", "", TicketPriority.HIGH, TicketStatus.OPEN, "D", TicketCategory.LOGISTICS, "2026-05-22")
        )

        val sorted = tickets.sortedForDisplay()

        assertEquals(listOf("Critical", "High newer", "High older", "Low"), sorted.map { it.title })
    }

    @Test
    fun createTicket_addsItemToRepositoryFlow() = runBlocking {
        val repository = TicketRepository()
        val request = CreateTicketRequest(
            title = "New supply issue",
            description = "Missing boxes at distribution hub",
            priority = TicketPriority.HIGH.name,
            status = TicketStatus.OPEN.name,
            provider = "Panini Test",
            category = TicketCategory.DISTRIBUTION.name,
            createdAt = ""
        )

        val result = repository.createTicket(request)

        assertTrue(result is com.app.copamundialfifa2026.data.repository.ApiResult.Success)
        val createdId = (result as com.app.copamundialfifa2026.data.repository.ApiResult.Success).data.id
        assertTrue(repository.tickets.value.any { it.id == createdId })
    }
}
