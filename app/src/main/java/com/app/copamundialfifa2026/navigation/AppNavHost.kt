package com.app.copamundialfifa2026.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.copamundialfifa2026.data.AppContainer
import com.app.copamundialfifa2026.ui.screens.createticket.CreateTicketViewModel
import com.app.copamundialfifa2026.ui.screens.login.LoginViewModel
import com.app.copamundialfifa2026.ui.screens.ticketdetail.TicketDetailViewModel
import com.app.copamundialfifa2026.ui.screens.ticketlist.TicketListViewModel
import com.app.copamundialfifa2026.ui.ViewModelFactories
import com.app.copamundialfifa2026.ui.screens.createticket.CreateTicketScreen
import com.app.copamundialfifa2026.ui.screens.login.LoginScreen
import com.app.copamundialfifa2026.ui.screens.ticketdetail.TicketDetailScreen
import com.app.copamundialfifa2026.ui.screens.ticketlist.TicketListScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.LOGIN
    ) {
        composable(AppDestinations.LOGIN) {
            val viewModel: LoginViewModel = viewModel(
                factory = ViewModelFactories.login(AppContainer.authRepository)
            )
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate(AppDestinations.TICKET_LIST) {
                        popUpTo(AppDestinations.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(AppDestinations.TICKET_LIST) {
            val viewModel: TicketListViewModel = viewModel(
                factory = ViewModelFactories.ticketList(AppContainer.ticketRepository)
            )
            TicketListScreen(
                viewModel = viewModel,
                onTicketClick = { ticketId ->
                    navController.navigate(AppDestinations.ticketDetail(ticketId))
                },
                onCreateTicket = {
                    navController.navigate(AppDestinations.CREATE_TICKET)
                },
                onLogout = {
                    AppContainer.authRepository.logout()
                    navController.navigate(AppDestinations.LOGIN) {
                        popUpTo(AppDestinations.TICKET_LIST) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = AppDestinations.TICKET_DETAIL_ROUTE,
            arguments = listOf(navArgument("ticketId") { type = NavType.StringType })
        ) { backStackEntry ->
            val ticketId = backStackEntry.arguments?.getString("ticketId").orEmpty()
            val viewModel: TicketDetailViewModel = viewModel(
                factory = ViewModelFactories.ticketDetail(AppContainer.ticketRepository, ticketId)
            )
            TicketDetailScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(AppDestinations.CREATE_TICKET) {
            val viewModel: CreateTicketViewModel = viewModel(
                factory = ViewModelFactories.createTicket(AppContainer.ticketRepository)
            )
            CreateTicketScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onTicketCreated = { navController.popBackStack() }
            )
        }
    }
}
