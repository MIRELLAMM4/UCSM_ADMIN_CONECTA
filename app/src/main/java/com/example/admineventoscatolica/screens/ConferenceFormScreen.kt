package com.example.admineventoscatolica.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.admineventoscatolica.model.Conference
import com.example.admineventoscatolica.services.addConference
import com.example.admineventoscatolica.services.updateConference

@Composable
fun ConferenceFormScreen(
    existingConference: Conference? = null,
    onConferenceSaved: () -> Unit
) {
    val context = LocalContext.current

    var title by remember { mutableStateOf(existingConference?.title ?: "") }
    var time by remember { mutableStateOf(existingConference?.time ?: "") }
    var date by remember { mutableStateOf(existingConference?.date ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título de la conferencia") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = time,
            onValueChange = { time = it },
            label = { Text("Hora") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val newConference = Conference(
                    id = existingConference?.id,
                    title = title,
                    time = time,
                    date = date,
                    upcoming = true
                )

                if (existingConference == null) {
                    addConference(newConference)
                    Toast.makeText(context, "Conferencia creada", Toast.LENGTH_SHORT).show()
                } else {
                    updateConference(newConference)
                    Toast.makeText(context, "Conferencia actualizada", Toast.LENGTH_SHORT).show()
                }

                onConferenceSaved()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(if (existingConference == null) "Crear" else "Actualizar")
        }
    }
}
