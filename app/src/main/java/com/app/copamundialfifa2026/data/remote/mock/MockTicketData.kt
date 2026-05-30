package com.app.copamundialfifa2026.data.remote.mock

import com.app.copamundialfifa2026.data.model.Ticket
import com.app.copamundialfifa2026.data.model.TicketCategory
import com.app.copamundialfifa2026.data.model.TicketPriority
import com.app.copamundialfifa2026.data.model.TicketStatus

object MockTicketData {
    fun seed(): List<Ticket> {
        return listOf(
            Ticket(
                id = "TCK-2026-001",
                title = "Missing 1,200 sticker packs from LOT-2026-0457",
                description = "Panini Mexico reported a shortage between warehouse dispatch and store receipt.",
                priority = TicketPriority.CRITICAL,
                status = TicketStatus.OPEN,
                provider = "Panini Mexico",
                category = TicketCategory.INVENTORY_SHORTAGE,
                createdAt = "2026-05-28"
            ),
            Ticket(
                id = "TCK-2026-002",
                title = "Delivery delay to San Jose Centro retail point",
                description = "DHL Supply Chain missed the agreed delivery window by 18 hours.",
                priority = TicketPriority.HIGH,
                status = TicketStatus.IN_PROGRESS,
                provider = "DHL Supply Chain",
                category = TicketCategory.LOGISTICS,
                createdAt = "2026-05-27"
            ),
            Ticket(
                id = "TCK-2026-003",
                title = "Duplicate stickers found in sealed boxes",
                description = "Packaging QA discovered repeated serials in one production sample.",
                priority = TicketPriority.MEDIUM,
                status = TicketStatus.OPEN,
                provider = "Panini Brazil",
                category = TicketCategory.PACKAGING,
                createdAt = "2026-05-26"
            ),
            Ticket(
                id = "TCK-2026-004",
                title = "Inventory mismatch in Alajuela warehouse",
                description = "Physical count differs from ERP by 430 units in outbound stock.",
                priority = TicketPriority.HIGH,
                status = TicketStatus.OPEN,
                provider = "Distribuidora Centroamerica S.A.",
                category = TicketCategory.INVENTORY_SHORTAGE,
                createdAt = "2026-05-25"
            ),
            Ticket(
                id = "TCK-2026-005",
                title = "Supplier has not confirmed special stickers restock",
                description = "Procurement team needs a formal response before print scheduling closes.",
                priority = TicketPriority.MEDIUM,
                status = TicketStatus.OPEN,
                provider = "Panini Italia",
                category = TicketCategory.PROVIDER,
                createdAt = "2026-05-24"
            ),
            Ticket(
                id = "TCK-2026-006",
                title = "Pending payment for Caribbean route carrier",
                description = "Finance must clear the invoice before the next logistics wave starts.",
                priority = TicketPriority.LOW,
                status = TicketStatus.RESOLVED,
                provider = "Transportes Limon",
                category = TicketCategory.PAYMENT,
                createdAt = "2026-05-23"
            ),
            Ticket(
                id = "TCK-2026-007",
                title = "Retail distributor requests urgent replenishment",
                description = "Distribution partner in Panama requested extra supply for weekend sales.",
                priority = TicketPriority.HIGH,
                status = TicketStatus.IN_PROGRESS,
                provider = "Panini Panama",
                category = TicketCategory.DISTRIBUTION,
                createdAt = "2026-05-22"
            ),
            Ticket(
                id = "TCK-2026-008",
                title = "Customs hold on imported collector album boxes",
                description = "Logistics team needs missing invoice metadata to release the container.",
                priority = TicketPriority.CRITICAL,
                status = TicketStatus.OPEN,
                provider = "Global Freight Alliance",
                category = TicketCategory.LOGISTICS,
                createdAt = "2026-05-21"
            ),
            Ticket(
                id = "TCK-2026-009",
                title = "Closed claim after missing barcode labels",
                description = "Previous claim was resolved after labels were reprinted and audited.",
                priority = TicketPriority.LOW,
                status = TicketStatus.CLOSED,
                provider = "Panini Costa Rica",
                category = TicketCategory.PROVIDER,
                createdAt = "2026-05-20"
            )
        )
    }
}
