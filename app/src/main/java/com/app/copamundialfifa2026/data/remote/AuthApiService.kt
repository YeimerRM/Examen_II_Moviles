package com.app.copamundialfifa2026.data.remote

import com.app.copamundialfifa2026.core.AppConstants
import com.app.copamundialfifa2026.data.remote.dto.LoginRequest
import com.app.copamundialfifa2026.data.remote.dto.UserDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST(AppConstants.Api.Paths.LOGIN)
    suspend fun login(@Body request: LoginRequest): UserDto
}
