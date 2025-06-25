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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.admineventoscatolica.R
import com.example.admineventoscatolica.model.Notice
import com.example.admineventoscatolica.services.deleteNotice
import com.example.admineventoscatolica.ui.theme.LightGrayDefault
import com.example.admineventoscatolica.ui.theme.Plomo
import com.example.admineventoscatolica.ui.theme.Rojo
import com.example.admineventoscatolica.ui.theme.VerdeSecundario

@Composable
fun NoticeCard(notice: Notice, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
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
            // Columna para la fecha de la noticia
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = notice.date ?: "00/00/000", fontSize = 12.sp, color = Color.Gray)
            }

            Spacer(Modifier.width(10.dp)) // Espacio horizontal entre las columnas

            // Columna para el contenido de la noticia
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = notice.title ?: "Título no disponible",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.philosopher_regular))
                )
                Spacer(Modifier.height(8.dp))


                Button(
                    onClick = { /* Acción para ver la noticia */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdeSecundario,
                        contentColor = Color.White
                    )
                ) {
                    Text("Ver")
                }

                Spacer(Modifier.height(8.dp))

                // Botones Editar y Eliminar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("noticeToEdit", notice)
                            navController.navigate("notice_form")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Plomo,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Editar")
                    }

                    Button(
                        onClick = {
                            notice.noticeid?.let { deleteNotice(it) }
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
