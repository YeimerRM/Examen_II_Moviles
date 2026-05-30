package com.app.copamundialfifa2026.ui.screens.ticketlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.copamundialfifa2026.core.FeatureFlags
import com.app.copamundialfifa2026.data.model.Ticket
import com.app.copamundialfifa2026.data.model.TicketStatus
import com.app.copamundialfifa2026.data.model.sortedForDisplay
import com.app.copamundialfifa2026.data.repository.TicketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TicketListUiState(
    val tickets: List<Ticket> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

class TicketListViewModel(
    ticketRepository: TicketRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TicketListUiState())
    val uiState: StateFlow<TicketListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            ticketRepository.tickets.collectLatest { tickets ->
                val visibleTickets = if (FeatureFlags.RESOLVED_TICKETS_VISIBLE) {
                    tickets
                } else {
                    tickets.filterNot {
                        it.status == TicketStatus.RESOLVED || it.status == TicketStatus.CLOSED
                    }
                }
                _uiState.update {
                    it.copy(
                        tickets = visibleTickets.sortedForDisplay(),
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }
        }
    }
}
