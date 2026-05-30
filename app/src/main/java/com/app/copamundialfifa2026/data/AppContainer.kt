package com.app.copamundialfifa2026.data

import com.app.copamundialfifa2026.data.repository.AuthRepository
import com.app.copamundialfifa2026.data.repository.TicketRepository

object AppContainer {
    val authRepository by lazy { AuthRepository() }
    val ticketRepository by lazy { TicketRepository() }
}
