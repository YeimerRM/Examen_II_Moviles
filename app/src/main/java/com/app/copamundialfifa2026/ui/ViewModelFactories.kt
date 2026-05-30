package com.app.copamundialfifa2026.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.app.copamundialfifa2026.data.repository.AuthRepository
import com.app.copamundialfifa2026.data.repository.TicketRepository
import com.app.copamundialfifa2026.ui.screens.createticket.CreateTicketViewModel
import com.app.copamundialfifa2026.ui.screens.login.LoginViewModel
import com.app.copamundialfifa2026.ui.screens.ticketdetail.TicketDetailViewModel
import com.app.copamundialfifa2026.ui.screens.ticketlist.TicketListViewModel

object ViewModelFactories {
    fun login(authRepository: AuthRepository): ViewModelProvider.Factory {
        return viewModelFactory {
            initializer {
                LoginViewModel(authRepository)
            }
        }
    }

    fun ticketList(ticketRepository: TicketRepository): ViewModelProvider.Factory {
        return viewModelFactory {
            initializer {
                TicketListViewModel(ticketRepository)
            }
        }
    }

    fun ticketDetail(ticketRepository: TicketRepository, ticketId: String): ViewModelProvider.Factory {
        return viewModelFactory {
            initializer {
                TicketDetailViewModel(ticketRepository, ticketId)
            }
        }
    }

    fun createTicket(ticketRepository: TicketRepository): ViewModelProvider.Factory {
        return viewModelFactory {
            initializer {
                CreateTicketViewModel(ticketRepository)
            }
        }
    }
}
