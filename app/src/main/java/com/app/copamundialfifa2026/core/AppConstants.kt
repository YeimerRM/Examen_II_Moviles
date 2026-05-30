package com.app.copamundialfifa2026.core

object AppConstants {
    object Api {
        const val BASE_URL = "https://api.panini-support.local/"

        object Paths {
            const val LOGIN = "auth/login"
            const val TICKETS = "tickets"
            const val TICKET_BY_ID = "tickets/{id}"
            const val TICKET_STATUS = "tickets/{id}/status"
            const val TICKET_PRIORITY = "tickets/{id}/priority"
        }
    }
}
