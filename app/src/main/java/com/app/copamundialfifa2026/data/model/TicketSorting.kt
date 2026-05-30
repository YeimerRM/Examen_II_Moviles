package com.app.copamundialfifa2026.data.model

fun List<Ticket>.sortedForDisplay(): List<Ticket> {
    return sortedWith(
        compareByDescending<Ticket> { it.priority.weight }
            .thenByDescending { it.createdAt }
    )
}
