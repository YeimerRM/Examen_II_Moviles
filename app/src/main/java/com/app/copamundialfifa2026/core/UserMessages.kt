package com.app.copamundialfifa2026.core

object UserMessages {
    object App {
        const val TITLE = "Panini Support"
        const val SUBTITLE = "Internal support tickets for FIFA World Cup 2026 operations"
        const val MOCK_CREDENTIALS = "Mock login: soporte@panini.com / Panini2026"
    }

    object Auth {
        const val EMAIL_LABEL = "Corporate email"
        const val PASSWORD_LABEL = "Password"
        const val LOGIN_BUTTON = "Sign in"
        const val LOGOUT_BUTTON = "Log out"
        const val INVALID_CREDENTIALS = "Invalid credentials. Use the mock Panini account."
        const val LOGIN_FAILED = "Unable to sign in right now."
        const val LOGIN_SUCCESS = "Signed in successfully."
    }

    object Ticket {
        const val LIST_TITLE = "Tickets"
        const val CREATE_TITLE = "Create ticket"
        const val DETAIL_TITLE = "Ticket detail"
        const val EMPTY_STATE = "No tickets available."
        const val CREATE_BUTTON = "Create ticket"
        const val SAVE_BUTTON = "Save ticket"
        const val UPDATE_STATUS = "Update status"
        const val UPDATE_PRIORITY = "Update priority"
        const val CREATED_SUCCESS = "Ticket created successfully."
        const val UPDATED_SUCCESS = "Ticket updated successfully."
        const val STATUS_OPEN = "Open"
        const val STATUS_IN_PROGRESS = "In progress"
        const val STATUS_RESOLVED = "Resolved"
        const val STATUS_CLOSED = "Closed"
    }

    object Validation {
        const val REQUIRED_FIELDS = "Please fill in all required fields."
        const val ID_NOT_FOUND = "The selected ticket could not be found."
    }
}
