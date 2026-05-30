package com.app.copamundialfifa2026.ui.screens.createticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.copamundialfifa2026.core.UserMessages
import com.app.copamundialfifa2026.data.model.TicketCategory
import com.app.copamundialfifa2026.data.model.TicketPriority
import com.app.copamundialfifa2026.data.model.TicketStatus
import com.app.copamundialfifa2026.data.remote.dto.CreateTicketRequest
import com.app.copamundialfifa2026.data.repository.ApiResult
import com.app.copamundialfifa2026.data.repository.TicketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CreateTicketUiState(
    val title: String = "",
    val description: String = "",
    val provider: String = "",
    val priority: TicketPriority = TicketPriority.HIGH,
    val status: TicketStatus = TicketStatus.OPEN,
    val category: TicketCategory = TicketCategory.LOGISTICS,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null,
    val wasCreated: Boolean = false
)

class CreateTicketViewModel(
    private val ticketRepository: TicketRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CreateTicketUiState())
    val uiState: StateFlow<CreateTicketUiState> = _uiState.asStateFlow()

    fun onTitleChange(value: String) {
        _uiState.update { it.copy(title = value, errorMessage = null) }
    }

    fun onDescriptionChange(value: String) {
        _uiState.update { it.copy(description = value, errorMessage = null) }
    }

    fun onProviderChange(value: String) {
        _uiState.update { it.copy(provider = value, errorMessage = null) }
    }

    fun onPriorityChange(value: TicketPriority) {
        _uiState.update { it.copy(priority = value, errorMessage = null) }
    }

    fun onStatusChange(value: TicketStatus) {
        _uiState.update { it.copy(status = value, errorMessage = null) }
    }

    fun onCategoryChange(value: TicketCategory) {
        _uiState.update { it.copy(category = value, errorMessage = null) }
    }

    fun submit() {
        val current = _uiState.value
        if (current.title.isBlank() || current.description.isBlank() || current.provider.isBlank()) {
            _uiState.update { it.copy(errorMessage = UserMessages.Validation.REQUIRED_FIELDS) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }
            val request = CreateTicketRequest(
                title = current.title.trim(),
                description = current.description.trim(),
                priority = current.priority.name,
                status = current.status.name,
                provider = current.provider.trim(),
                category = current.category.name,
                createdAt = ""
            )
            when (val result = ticketRepository.createTicket(request)) {
                is ApiResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            successMessage = UserMessages.Ticket.CREATED_SUCCESS,
                            wasCreated = true
                        )
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

    fun consumeNavigationEvent() {
        _uiState.update { it.copy(wasCreated = false, successMessage = null) }
    }
}
