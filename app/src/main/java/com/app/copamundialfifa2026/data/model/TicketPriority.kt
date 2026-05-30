package com.app.copamundialfifa2026.data.model

enum class TicketPriority(val weight: Int, val label: String) {
    CRITICAL(4, "Critical"),
    HIGH(3, "High"),
    MEDIUM(2, "Medium"),
    LOW(1, "Low");

    companion object {
        fun fromLabel(value: String): TicketPriority {
            return entries.firstOrNull { it.name == value || it.label.equals(value, ignoreCase = true) }
                ?: MEDIUM
        }
    }
}
