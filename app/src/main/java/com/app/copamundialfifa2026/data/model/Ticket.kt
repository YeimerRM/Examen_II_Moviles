package com.app.copamundialfifa2026.data.model

data class Ticket(
    val id: String,
    val title: String,
    val description: String,
    val priority: TicketPriority,
    val status: TicketStatus,
    val provider: String,
    val category: TicketCategory,
    val createdAt: String
)
