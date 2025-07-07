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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.admineventoscatolica.R
import com.example.admineventoscatolica.model.Notice
import com.example.admineventoscatolica.services.deleteNotice
import com.example.admineventoscatolica.ui.theme.LightGrayDefault
import com.example.admineventoscatolica.ui.theme.Plomo
import com.example.admineventoscatolica.ui.theme.Rojo
import com.example.admineventoscatolica.ui.theme.VerdeSecundario

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NoticeCard(notice: Notice, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
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
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Columna para la fecha de la noticia
            Column(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GlideImage(
                    model = notice.image,
                    contentDescription = "Imagen de la noticia",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ) { requestBuilder ->
                    requestBuilder
                        .placeholder(R.mipmap.no_image_logo)
                        .error(R.mipmap.error_image_logo)
                }
                Text(
                    text = notice.date ?: "00/00/000",
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
            }

            Spacer(Modifier.width(10.dp)) // Espacio horizontal entre las columnas

            // Columna para el contenido de la noticia
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = notice.title ?: "Título no disponible",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.philosopher_regular)),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))


                Button(
                    onClick = { /* Acción para ver la noticia */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdeSecundario,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver")
                }

                // Botones Editar y Eliminar
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
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
                        ),
                        modifier = Modifier.weight(1f)
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
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Eliminar",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}
