package com.app.copamundialfifa2026.ui.screens.createticket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.copamundialfifa2026.core.UserMessages
import com.app.copamundialfifa2026.data.model.TicketCategory
import com.app.copamundialfifa2026.data.model.TicketPriority
import com.app.copamundialfifa2026.data.model.TicketStatus
import com.app.copamundialfifa2026.ui.components.AppButton
import com.app.copamundialfifa2026.ui.components.AppScaffold
import com.app.copamundialfifa2026.ui.components.AppTextField
import com.app.copamundialfifa2026.ui.components.LabeledDropdown

@Composable
fun CreateTicketScreen(
    viewModel: CreateTicketViewModel,
    onBack: () -> Unit,
    onTicketCreated: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.wasCreated) {
        if (uiState.wasCreated) {
            onTicketCreated()
            viewModel.consumeNavigationEvent()
        }
    }

    AppScaffold(
        title = UserMessages.Ticket.CREATE_TITLE,
        onBackClick = onBack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(colors = CardDefaults.cardColors()) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AppTextField(
                        value = uiState.title,
                        onValueChange = viewModel::onTitleChange,
                        label = "Title"
                    )
                    AppTextField(
                        value = uiState.description,
                        onValueChange = viewModel::onDescriptionChange,
                        label = "Description",
                        singleLine = false
                    )
                    AppTextField(
                        value = uiState.provider,
                        onValueChange = viewModel::onProviderChange,
                        label = "Provider"
                    )
                    LabeledDropdown(
                        label = "Priority",
                        selectedValue = uiState.priority,
                        options = TicketPriority.entries.toList(),
                        onOptionSelected = viewModel::onPriorityChange,
                        optionLabel = { it.label }
                    )
                    LabeledDropdown(
                        label = "Status",
                        selectedValue = uiState.status,
                        options = TicketStatus.entries.toList(),
                        onOptionSelected = viewModel::onStatusChange,
                        optionLabel = { it.label }
                    )
                    LabeledDropdown(
                        label = "Category",
                        selectedValue = uiState.category,
                        options = TicketCategory.entries.toList(),
                        onOptionSelected = viewModel::onCategoryChange,
                        optionLabel = { it.label }
                    )
                    AppButton(
                        text = if (uiState.isLoading) "Saving..." else UserMessages.Ticket.SAVE_BUTTON,
                        onClick = viewModel::submit,
                        enabled = !uiState.isLoading
                    )
                    uiState.errorMessage?.let {
                        Text(text = it, color = MaterialTheme.colorScheme.error)
                    }
                    uiState.successMessage?.let {
                        Text(text = it, color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}
