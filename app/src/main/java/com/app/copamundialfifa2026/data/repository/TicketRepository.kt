package com.app.copamundialfifa2026.data.repository

import com.app.copamundialfifa2026.core.UserMessages
import com.app.copamundialfifa2026.data.model.Ticket
import com.app.copamundialfifa2026.data.model.TicketCategory
import com.app.copamundialfifa2026.data.model.TicketPriority
import com.app.copamundialfifa2026.data.model.TicketStatus
import com.app.copamundialfifa2026.data.remote.dto.CreateTicketRequest
import com.app.copamundialfifa2026.data.remote.mock.MockTicketData
import java.util.UUID
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class TicketRepository {
    private val _tickets = MutableStateFlow(MockTicketData.seed())
    val tickets: StateFlow<List<Ticket>> = _tickets.asStateFlow()

    suspend fun createTicket(request: CreateTicketRequest): ApiResult<Ticket> {
        delay(450)
        val ticket = Ticket(
            id = "TCK-" + UUID.randomUUID().toString().take(8).uppercase(),
            title = request.title,
            description = request.description,
            priority = TicketPriority.fromLabel(request.priority),
            status = TicketStatus.fromLabel(request.status),
            provider = request.provider,
            category = TicketCategory.fromLabel(request.category),
            createdAt = LocalDate.now().toString()
        )
        _tickets.update { current -> listOf(ticket) + current }
        return ApiResult.Success(ticket)
    }

    suspend fun updateStatus(ticketId: String, newStatus: TicketStatus): ApiResult<Ticket> {
        delay(350)
        val updated = mutateTicket(ticketId) { it.copy(status = newStatus) }
            ?: return ApiResult.Error(UserMessages.Validation.ID_NOT_FOUND, 404)
        return ApiResult.Success(updated)
    }

    suspend fun updatePriority(ticketId: String, newPriority: TicketPriority): ApiResult<Ticket> {
        delay(350)
        val updated = mutateTicket(ticketId) { it.copy(priority = newPriority) }
            ?: return ApiResult.Error(UserMessages.Validation.ID_NOT_FOUND, 404)
        return ApiResult.Success(updated)
    }

    fun getTicket(ticketId: String): Ticket? {
        return _tickets.value.firstOrNull { it.id == ticketId }
    }

    private fun mutateTicket(ticketId: String, mutation: (Ticket) -> Ticket): Ticket? {
        var updatedTicket: Ticket? = null
        _tickets.update { current ->
            current.map { ticket ->
                if (ticket.id == ticketId) {
                    val updated = mutation(ticket)
                    updatedTicket = updated
                    updated
                } else {
                    ticket
                }
            }
        }
        return updatedTicket
    }
}
