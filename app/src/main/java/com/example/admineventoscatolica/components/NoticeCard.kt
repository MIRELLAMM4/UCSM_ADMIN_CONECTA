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
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = notice.date ?: "Fecha no disponible",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Text(
                text = notice.title ?: "Título no disponible",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.philosopher_regular)),
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Text(
                text = notice.description ?: "Descripción no disponible",
                fontSize = 14.sp,
                color = Color.DarkGray,
                maxLines = 3
            )

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
