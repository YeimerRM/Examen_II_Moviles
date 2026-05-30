package com.app.copamundialfifa2026.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.copamundialfifa2026.data.model.TicketCategory
import com.app.copamundialfifa2026.data.model.TicketPriority
import com.app.copamundialfifa2026.data.model.TicketStatus

@Composable
fun PriorityChip(priority: TicketPriority, modifier: Modifier = Modifier) {
    val (bg, fg) = when (priority) {
        TicketPriority.CRITICAL -> Color(0xFFFFDAD8) to Color(0xFF93000A)
        TicketPriority.HIGH     -> Color(0xFFFFEDD5) to Color(0xFF9A3412)
        TicketPriority.MEDIUM   -> Color(0xFFFEF9C3) to Color(0xFF854D0E)
        TicketPriority.LOW      -> Color(0xFFF1F5F9) to Color(0xFF475569)
    }
    BadgeChip(text = priority.label, background = bg, foreground = fg, modifier = modifier)
}

@Composable
fun StatusChip(status: TicketStatus, modifier: Modifier = Modifier) {
    val (bg, fg) = when (status) {
        TicketStatus.OPEN        -> Color(0xFFDBEAFE) to Color(0xFF1E40AF)
        TicketStatus.IN_PROGRESS -> Color(0xFFCCFBF1) to Color(0xFF0F766E)
        TicketStatus.RESOLVED    -> Color(0xFFDCFCE7) to Color(0xFF166534)
        TicketStatus.CLOSED      -> Color(0xFFF1F5F9) to Color(0xFF475569)
    }
    BadgeChip(text = status.label, background = bg, foreground = fg, modifier = modifier)
}

@Composable
fun CategoryChip(category: TicketCategory, modifier: Modifier = Modifier) {
    BadgeChip(
        text       = category.label,
        background = Color(0xFFEDE9FE),
        foreground = Color(0xFF5B21B6),
        modifier   = modifier
    )
}

@Composable
private fun BadgeChip(
    text: String,
    background: Color,
    foreground: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = background,
        modifier = modifier
    ) {
        Text(
            text     = text,
            style    = MaterialTheme.typography.labelMedium,
            color    = foreground,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}
