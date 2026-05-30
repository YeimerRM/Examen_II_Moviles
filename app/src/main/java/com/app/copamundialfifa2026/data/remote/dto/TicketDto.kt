package com.app.copamundialfifa2026.data.remote.dto

import com.app.copamundialfifa2026.data.model.Ticket
import com.app.copamundialfifa2026.data.model.TicketCategory
import com.app.copamundialfifa2026.data.model.TicketPriority
import com.app.copamundialfifa2026.data.model.TicketStatus

data class TicketDto(
    val id: String,
    val title: String,
    val description: String,
    val priority: String,
    val status: String,
    val provider: String,
    val category: String,
    val createdAt: String
)

fun TicketDto.toDomain(): Ticket {
    return Ticket(
        id = id,
        title = title,
        description = description,
        priority = TicketPriority.fromLabel(priority),
        status = TicketStatus.fromLabel(status),
        provider = provider,
        category = TicketCategory.fromLabel(category),
        createdAt = createdAt
    )
}

fun Ticket.toDto(): TicketDto {
    return TicketDto(
        id = id,
        title = title,
        description = description,
        priority = priority.name,
        status = status.name,
        provider = provider,
        category = category.name,
        createdAt = createdAt
    )
}
