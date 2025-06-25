package com.example.admineventoscatolica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.admineventoscatolica.components.Body
import com.example.admineventoscatolica.components.Header
import com.example.admineventoscatolica.components.NavBottom
import com.example.admineventoscatolica.model.Conference
import com.example.admineventoscatolica.model.Events
import com.example.admineventoscatolica.model.Notice
import com.example.admineventoscatolica.navigation.AppNavGraph
import com.example.admineventoscatolica.states.NavItem
import com.example.admineventoscatolica.ui.theme.EventosCatolicaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        enableEdgeToEdge()

        setContent {
            EventosCatolicaTheme {
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}

@Composable
fun AppMain(navController: NavController) {
    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf(NavItem.EVENTOS) }

    LaunchedEffect(navController.currentBackStackEntry) {
        val selected = navController.currentBackStackEntry
            ?.savedStateHandle
            ?.get<NavItem>("selectedNavItem")
        selected?.let {
            selectedItem = it
            navController.currentBackStackEntry
                ?.savedStateHandle
                ?.remove<NavItem>("selectedNavItem")
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { Header() },
        bottomBar = {
            NavBottom(
                context = context,
                selectedItem = selectedItem,
                onItemSelected = { newItem ->
                    selectedItem = newItem
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                when (selectedItem) {
                    NavItem.EVENTOS -> {
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.remove<Events>("eventToEdit")
                        navController.navigate("event_form")
                    }

                    NavItem.CONFERENCIAS -> {
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.remove<Conference>("conferenceToEdit")
                        navController.navigate("conference_form")
                    }

                    NavItem.NOTICIAS -> {
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.remove<Notice>("noticeToEdit")
                        navController.navigate("notice_form")
                    }

                    else -> Unit
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { innerPadding ->
        Body(
            modifier = Modifier.padding(innerPadding),
            context = context,
            navController = navController,
            selectedItem = selectedItem
        )
    }
}
