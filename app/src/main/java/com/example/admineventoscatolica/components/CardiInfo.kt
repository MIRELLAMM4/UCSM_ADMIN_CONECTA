package com.example.admineventoscatolica.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.admineventoscatolica.R
import com.example.admineventoscatolica.model.Events
import com.example.admineventoscatolica.services.deleteEvent
import com.example.admineventoscatolica.ui.theme.LightGrayDefault
import com.example.admineventoscatolica.ui.theme.Plomo
import com.example.admineventoscatolica.ui.theme.Purple40
import com.example.admineventoscatolica.ui.theme.Rojo
import com.example.admineventoscatolica.ui.theme.VerdeButton
import com.example.admineventoscatolica.ui.theme.VerdeSecundario

@Composable
fun CardInfo(event: Events, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .border(
                width = 0.5.dp,
                color = LightGrayDefault,
                shape = RoundedCornerShape(8.dp)
            ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = event.time ?: "00:00", fontSize = 14.sp, color = Color.Gray)
                Text(text = event.date ?: "00/00/000", fontSize = 12.sp, color = Color.Gray)
            }

            Spacer(Modifier.width(10.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = event.title ?: "Título no disponible",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.philosopher_regular)),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(8.dp))

                PersonalizationButton(VerdeSecundario)
                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Botón Editar
                    Button(
                        onClick = {
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("eventToEdit", event)
                            navController.navigate("event_form")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Plomo,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Editar")
                    }

                    // Botón Eliminar
                    Button(
                        onClick = {
                            event.eventid?.let { deleteEvent(it) }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Rojo,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Eliminar")
                    }
                }

            }
        }
    }
}
