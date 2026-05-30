package com.app.copamundialfifa2026.navigation

object AppDestinations {
    const val LOGIN = "login"
    const val TICKET_LIST = "tickets"
    const val TICKET_DETAIL_ROUTE = "ticket/{ticketId}"
    const val CREATE_TICKET = "ticket/create"

    fun ticketDetail(ticketId: String): String = "ticket/$ticketId"
}
