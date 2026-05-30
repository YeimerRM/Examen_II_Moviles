package com.app.copamundialfifa2026.ui.screens.ticketdetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.copamundialfifa2026.core.FeatureFlags
import com.app.copamundialfifa2026.core.UserMessages
import com.app.copamundialfifa2026.data.model.TicketPriority
import com.app.copamundialfifa2026.data.model.TicketStatus
import com.app.copamundialfifa2026.ui.components.AppButton
import com.app.copamundialfifa2026.ui.components.AppScaffold
import com.app.copamundialfifa2026.ui.components.CategoryChip
import com.app.copamundialfifa2026.ui.components.LabeledDropdown
import com.app.copamundialfifa2026.ui.components.PriorityChip
import com.app.copamundialfifa2026.ui.components.StatusChip

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TicketDetailScreen(
    viewModel: TicketDetailViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.successMessage) {
        if (uiState.successMessage != null) viewModel.consumeMessage()
    }

    AppScaffold(
        title       = UserMessages.Ticket.DETAIL_TITLE,
        onBackClick = onBack
    ) { innerPadding ->
        val ticket = uiState.ticket
        if (ticket == null) {
            uiState.errorMessage?.let {
                Surface(
                    color    = MaterialTheme.colorScheme.errorContainer,
                    shape    = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    Text(
                        text     = it,
                        color    = MaterialTheme.colorScheme.onErrorContainer,
                        style    = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            return@AppScaffold
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // ── Info card ─────────────────────────────────────────────
            Card(
                shape     = RoundedCornerShape(16.dp),
                colors    = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text     = ticket.title,
                            style    = MaterialTheme.typography.headlineSmall,
                            color    = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text     = "#${ticket.id.takeLast(4)}",
                            style    = MaterialTheme.typography.labelSmall,
                            color    = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                        )
                    }

                    Text(
                        text  = ticket.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement   = Arrangement.spacedBy(6.dp)
                    ) {
                        PriorityChip(ticket.priority)
                        StatusChip(ticket.status)
                        CategoryChip(ticket.category)
                    }

                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector        = Icons.Outlined.Business,
                                contentDescription = null,
                                tint               = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier           = Modifier.size(16.dp)
                            )
                            Text(
                                text  = ticket.provider,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector        = Icons.Outlined.Schedule,
                                contentDescription = null,
                                tint               = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier           = Modifier.size(16.dp)
                            )
                            Text(
                                text  = ticket.createdAt,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // ── Status card ───────────────────────────────────────────
            Card(
                shape     = RoundedCornerShape(16.dp),
                colors    = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text  = "Cambiar estado",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        TicketStatus.entries.chunked(2).forEach { row ->
                            Row(
                                modifier              = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                row.forEach { status ->
                                    val isSelected = ticket.status == status
                                    if (isSelected) {
                                        Button(
                                            onClick   = { viewModel.updateStatus(status) },
                                            modifier  = Modifier.weight(1f),
                                            enabled   = !uiState.isLoading,
                                            colors    = ButtonDefaults.buttonColors(
                                                containerColor = MaterialTheme.colorScheme.primary
                                            )
                                        ) {
                                            Icon(
                                                imageVector        = Icons.Outlined.CheckCircle,
                                                contentDescription = null,
                                                modifier           = Modifier
                                                    .size(14.dp)
                                                    .padding(end = 0.dp)
                                            )
                                            Text(
                                                text     = status.label,
                                                style    = MaterialTheme.typography.labelMedium,
                                                modifier = Modifier.padding(start = 4.dp)
                                            )
                                        }
                                    } else {
                                        OutlinedButton(
                                            onClick   = { viewModel.updateStatus(status) },
                                            modifier  = Modifier.weight(1f),
                                            enabled   = !uiState.isLoading
                                        ) {
                                            Text(
                                                text  = status.label,
                                                style = MaterialTheme.typography.labelMedium
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // ── Priority card ─────────────────────────────────────────
            if (FeatureFlags.PRIORITY_UPDATE_ENABLED) {
                Card(
                    shape     = RoundedCornerShape(16.dp),
                    colors    = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text  = "Cambiar prioridad",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        LabeledDropdown(
                            label           = "Prioridad",
                            selectedValue   = uiState.selectedPriority,
                            options         = TicketPriority.entries.toList(),
                            onOptionSelected = viewModel::onPrioritySelected,
                            optionLabel     = { it.label }
                        )
                        AppButton(
                            text      = "Aplicar prioridad",
                            onClick   = viewModel::updatePriority,
                            enabled   = !uiState.isLoading,
                            isLoading = uiState.isLoading
                        )
                    }
                }
            }

            // ── Feedback messages ─────────────────────────────────────
            AnimatedVisibility(visible = uiState.errorMessage != null) {
                uiState.errorMessage?.let {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.errorContainer
                    ) {
                        Text(
                            text     = it,
                            color    = MaterialTheme.colorScheme.onErrorContainer,
                            style    = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(14.dp)
                        )
                    }
                }
            }
            AnimatedVisibility(visible = uiState.successMessage != null) {
                uiState.successMessage?.let {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.tertiaryContainer
                    ) {
                        Text(
                            text     = it,
                            color    = MaterialTheme.colorScheme.onTertiaryContainer,
                            style    = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(14.dp)
                        )
                    }
                }
            }
        }
    }
}
