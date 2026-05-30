package com.app.copamundialfifa2026.data.remote.dto

data class CreateTicketRequest(
    val title: String,
    val description: String,
    val priority: String,
    val status: String,
    val provider: String,
    val category: String,
    val createdAt: String
)

data class UpdateStatusRequest(
    val status: String
)

data class UpdatePriorityRequest(
    val priority: String
)
