package com.app.copamundialfifa2026.data

import com.app.copamundialfifa2026.data.remote.dto.UserDto

object AuthSession {
    var currentUser: UserDto? = null
        private set

    fun setUser(user: UserDto) {
        currentUser = user
    }

    fun clear() {
        currentUser = null
    }
}
