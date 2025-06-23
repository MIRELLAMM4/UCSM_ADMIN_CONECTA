package com.example.admineventoscatolica.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.admineventoscatolica.model.Events
import com.example.admineventoscatolica.services.addEvent
import com.example.admineventoscatolica.services.updateEvent

@Composable
fun EventFormScreen(
    existingEvent: Events? = null,
    onEventSaved: () -> Unit
) {
    val context = LocalContext.current

    var title by remember { mutableStateOf(existingEvent?.title ?: "") }
    var time by remember { mutableStateOf(existingEvent?.time ?: "") }
    var date by remember { mutableStateOf(existingEvent?.date ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título del evento") },
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
                val event = Events(
                    eventid = existingEvent?.eventid,
                    title = title,
                    time = time,
                    date = date,
                    upcoming = true
                )

                if (event.eventid == null) {
                    addEvent(event)
                    Toast.makeText(context, "Evento creado", Toast.LENGTH_SHORT).show()
                } else {
                    updateEvent(event)
                    Toast.makeText(context, "Evento actualizado", Toast.LENGTH_SHORT).show()
                }
                onEventSaved()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(if (existingEvent == null) "Crear" else "Actualizar")
        }
    }
}
