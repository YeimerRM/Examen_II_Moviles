package com.app.copamundialfifa2026.data.model

enum class TicketStatus(val label: String) {
    OPEN("Open"),
    IN_PROGRESS("In progress"),
    RESOLVED("Resolved"),
    CLOSED("Closed");

    companion object {
        fun fromLabel(value: String): TicketStatus {
            return entries.firstOrNull { it.name == value || it.label.equals(value, ignoreCase = true) }
                ?: OPEN
        }
    }
}
