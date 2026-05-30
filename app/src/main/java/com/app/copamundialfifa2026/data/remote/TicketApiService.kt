package com.app.copamundialfifa2026.data.remote

import com.app.copamundialfifa2026.core.AppConstants
import com.app.copamundialfifa2026.data.remote.dto.CreateTicketRequest
import com.app.copamundialfifa2026.data.remote.dto.TicketDto
import com.app.copamundialfifa2026.data.remote.dto.UpdatePriorityRequest
import com.app.copamundialfifa2026.data.remote.dto.UpdateStatusRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TicketApiService {
    @GET(AppConstants.Api.Paths.TICKETS)
    suspend fun getTickets(): List<TicketDto>

    @GET(AppConstants.Api.Paths.TICKET_BY_ID)
    suspend fun getTicket(@Path("id") id: String): TicketDto

    @POST(AppConstants.Api.Paths.TICKETS)
    suspend fun createTicket(@Body request: CreateTicketRequest): TicketDto

    @PATCH(AppConstants.Api.Paths.TICKET_STATUS)
    suspend fun updateStatus(@Path("id") id: String, @Body request: UpdateStatusRequest): TicketDto

    @PATCH(AppConstants.Api.Paths.TICKET_PRIORITY)
    suspend fun updatePriority(@Path("id") id: String, @Body request: UpdatePriorityRequest): TicketDto
}
