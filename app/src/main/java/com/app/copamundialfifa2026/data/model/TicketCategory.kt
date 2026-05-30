package com.app.copamundialfifa2026.data.model

enum class TicketCategory(val label: String) {
    INVENTORY_SHORTAGE("Inventory shortage"),
    DISTRIBUTION("Distribution"),
    LOGISTICS("Logistics"),
    PROVIDER("Provider"),
    PACKAGING("Packaging"),
    PAYMENT("Payment");

    companion object {
        fun fromLabel(value: String): TicketCategory {
            return entries.firstOrNull { it.name == value || it.label.equals(value, ignoreCase = true) }
                ?: INVENTORY_SHORTAGE
        }
    }
}
