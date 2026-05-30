package com.app.copamundialfifa2026.ui.screens.ticketdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.copamundialfifa2026.core.UserMessages
import com.app.copamundialfifa2026.data.model.Ticket
import com.app.copamundialfifa2026.data.model.TicketPriority
import com.app.copamundialfifa2026.data.model.TicketStatus
import com.app.copamundialfifa2026.data.repository.ApiResult
import com.app.copamundialfifa2026.data.repository.TicketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TicketDetailUiState(
    val ticket: Ticket? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val successMessage: String? = null,
    val selectedPriority: TicketPriority = TicketPriority.MEDIUM
)

class TicketDetailViewModel(
    private val ticketRepository: TicketRepository,
    private val ticketId: String
) : ViewModel() {
    private val _uiState = MutableStateFlow(TicketDetailUiState())
    val uiState: StateFlow<TicketDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            ticketRepository.tickets.collectLatest { tickets ->
                val ticket = tickets.firstOrNull { it.id == ticketId }
                _uiState.update {
                    it.copy(
                        ticket = ticket,
                        isLoading = false,
                        errorMessage = if (ticket == null) UserMessages.Validation.ID_NOT_FOUND else null,
                        selectedPriority = ticket?.priority ?: it.selectedPriority
                    )
                }
            }
        }
    }

    fun onPrioritySelected(priority: TicketPriority) {
        _uiState.update { it.copy(selectedPriority = priority, errorMessage = null, successMessage = null) }
    }

    fun updateStatus(status: TicketStatus) {
        val ticket = _uiState.value.ticket ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }
            when (val result = ticketRepository.updateStatus(ticket.id, status)) {
                is ApiResult.Success -> {
                    _uiState.update {
                        it.copy(isLoading = false, successMessage = UserMessages.Ticket.UPDATED_SUCCESS)
                    }
                }
                is ApiResult.Error -> {
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    fun updatePriority() {
        val ticket = _uiState.value.ticket ?: return
        val priority = _uiState.value.selectedPriority
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }
            when (val result = ticketRepository.updatePriority(ticket.id, priority)) {
                is ApiResult.Success -> {
                    _uiState.update {
                        it.copy(isLoading = false, successMessage = UserMessages.Ticket.UPDATED_SUCCESS)
                    }
                }
                is ApiResult.Error -> {
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    fun consumeMessage() {
        _uiState.update { it.copy(errorMessage = null, successMessage = null) }
    }
}
