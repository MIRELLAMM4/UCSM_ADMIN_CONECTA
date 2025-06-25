package com.example.admineventoscatolica.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.admineventoscatolica.model.Notice
import com.example.admineventoscatolica.services.addNotice
import com.example.admineventoscatolica.services.updateNotice
import androidx.navigation.NavController

@Composable
fun NoticeFormScreen(
    navController: NavController,  // Asegúrate de que navController esté aquí
    existingNotice: Notice? = null,
    onNoticeSaved: () -> Unit
) {
    val context = LocalContext.current

    var title by remember { mutableStateOf(existingNotice?.title ?: "") }
    var description by remember { mutableStateOf(existingNotice?.description ?: "") }
    var date by remember { mutableStateOf(existingNotice?.date ?: "") }
    var imageUrl by remember { mutableStateOf(existingNotice?.image ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título de la noticia") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("URL de la imagen") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val newNotice = Notice(
                    noticeid = existingNotice?.noticeid,
                    title = title,
                    description = description,
                    date = date,
                    image = imageUrl,
                    isUpcoming = true
                )

                if (existingNotice == null) {
                    addNotice(newNotice)
                    Toast.makeText(context, "Noticia creada", Toast.LENGTH_SHORT).show()
                } else {
                    updateNotice(newNotice)
                    Toast.makeText(context, "Noticia actualizada", Toast.LENGTH_SHORT).show()
                }

                onNoticeSaved()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(if (existingNotice == null) "Crear" else "Actualizar")
        }
    }
}
