package com.app.copamundialfifa2026.ui.screens.ticketlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.copamundialfifa2026.core.FeatureFlags
import com.app.copamundialfifa2026.core.UserMessages
import com.app.copamundialfifa2026.ui.components.AppScaffold
import com.app.copamundialfifa2026.ui.components.TicketCard

@Composable
fun TicketListScreen(
    viewModel: TicketListViewModel,
    onTicketClick: (String) -> Unit,
    onCreateTicket: () -> Unit,
    onLogout: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AppScaffold(
        title = UserMessages.Ticket.LIST_TITLE,
        actions = {
            IconButton(onClick = onLogout) {
                Icon(
                    imageVector        = Icons.Outlined.Logout,
                    contentDescription = UserMessages.Auth.LOGOUT_BUTTON,
                    tint               = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        floatingActionButton = if (FeatureFlags.CREATE_TICKET_ENABLED) {
            {
                FloatingActionButton(
                    onClick           = onCreateTicket,
                    containerColor    = MaterialTheme.colorScheme.primary,
                    contentColor      = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(
                        imageVector        = Icons.Outlined.Add,
                        contentDescription = "Crear ticket"
                    )
                }
            }
        } else null
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            uiState.tickets.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text  = "📋",
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Text(
                            text  = UserMessages.Ticket.EMPTY_STATE,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            else -> {
                LazyColumn(
                    modifier        = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentPadding  = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item {
                        Text(
                            text     = "${uiState.tickets.size} tickets activos",
                            style    = MaterialTheme.typography.labelMedium,
                            color    = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 4.dp, start = 4.dp)
                        )
                    }
                    items(uiState.tickets, key = { it.id }) { ticket ->
                        TicketCard(
                            ticket  = ticket,
                            onClick = { onTicketClick(ticket.id) }
                        )
                    }
                }
            }
        }
    }
}
