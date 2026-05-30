package com.app.copamundialfifa2026.data.remote.dto

data class LoginRequest(
    val email: String,
    val password: String
)

data class UserDto(
    val id: String,
    val name: String,
    val email: String
)
