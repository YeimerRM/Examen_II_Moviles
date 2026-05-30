package com.app.copamundialfifa2026.data.repository

import com.app.copamundialfifa2026.core.UserMessages
import com.app.copamundialfifa2026.data.AuthSession
import com.app.copamundialfifa2026.data.remote.dto.UserDto
import kotlinx.coroutines.delay

class AuthRepository {
    suspend fun login(email: String, password: String): ApiResult<UserDto> {
        delay(400)
        return if (
            email.equals("soporte@panini.com", ignoreCase = true) &&
            password == "Panini2026"
        ) {
            val user = UserDto(
                id = "user-panini-support",
                name = "Panini Support Agent",
                email = "soporte@panini.com"
            )
            AuthSession.setUser(user)
            ApiResult.Success(user)
        } else {
            ApiResult.Error(UserMessages.Auth.INVALID_CREDENTIALS, 401)
        }
    }

    fun logout() {
        AuthSession.clear()
    }
}
