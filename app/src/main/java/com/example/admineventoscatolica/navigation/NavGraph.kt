package com.example.admineventoscatolica.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.admineventoscatolica.AppMain
import com.example.admineventoscatolica.model.Events
import com.example.admineventoscatolica.screens.EventFormScreen
import com.example.admineventoscatolica.model.Conference
import com.example.admineventoscatolica.screens.ConferenceFormScreen
import com.example.admineventoscatolica.model.Notice
import com.example.admineventoscatolica.screens.NoticeFormScreen
import com.example.admineventoscatolica.states.NavItem

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            AppMain(navController = navController) // Asegúrate de pasar navController aquí
        }
        composable("event_form") {
            val eventToEdit = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<Events>("eventToEdit")

            EventFormScreen(
                existingEvent = eventToEdit,
                onEventSaved = {
                    navController.popBackStack()
                }
            )
        }
        composable("conference_form") {
            val conferenceToEdit = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Conference>("conferenceToEdit")

            ConferenceFormScreen(
                existingConference = conferenceToEdit,
                onConferenceSaved = {
                    navController.popBackStack()
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("selectedNavItem", NavItem.CONFERENCIAS)
                }
            )
        }
        composable("notice_form") {
            val noticeToEdit = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Notice>("noticeToEdit")

            // Asegúrate de pasar el navController aquí
            NoticeFormScreen(
                navController = navController,  // Pasa el navController
                existingNotice = noticeToEdit,
                onNoticeSaved = {
                    navController.popBackStack()
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("selectedNavItem", NavItem.NOTICIAS)
                }
            )
        }
    }
}
