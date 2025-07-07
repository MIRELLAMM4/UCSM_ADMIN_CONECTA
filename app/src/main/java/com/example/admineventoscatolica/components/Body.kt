package com.example.admineventoscatolica.components

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.admineventoscatolica.model.Conference
import com.example.admineventoscatolica.model.Events
import com.example.admineventoscatolica.model.Notice
import com.example.admineventoscatolica.services.ConnectFirebase
import com.example.admineventoscatolica.states.NavItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*

@Composable
fun Body(
    modifier: Modifier = Modifier,
    context: Context,
    navController: NavController,
    selectedItem: NavItem
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        when (selectedItem) {
            NavItem.EVENTOS -> {
                EventBody(context = context, navController = navController)
            }
            NavItem.CONFERENCIAS -> {
                ConferenceBody(context = context, navController = navController)
            }
            NavItem.NOTICIAS -> {
                NoticeBody(context = context, navController = navController)
            }
        }
    }
}

@Composable
private fun ConferenceBody(context: Context, navController: NavController) {
    val conferencias = remember { mutableStateListOf<Conference>() }

    LaunchedEffect(Unit) {
        val ref = ConnectFirebase().child("conferencias")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                conferencias.clear()
                snapshot.children.forEach { child ->
                    val conf = child.getValue(Conference::class.java)
                    conf?.let {
                        conferencias.add(it.copy(id = child.key))
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error al cargar conferencias", Toast.LENGTH_SHORT).show()
            }
        })
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SectionTitle("Conferencias")
        if (conferencias.isEmpty()) {
            Text("No hay conferencias disponibles", modifier = Modifier.padding(16.dp))
        } else {
            conferencias.filter { it.upcoming }.forEach { conference ->
                ConferenceCard(conference = conference, navController = navController)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun EventBody(context: Context, navController: NavController) {
    val eventos = remember { mutableStateListOf<Events>() }

    LaunchedEffect(Unit) {
        val ref = ConnectFirebase().child("eventos")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                eventos.clear()
                snapshot.children.forEach { child ->
                    val evento = child.getValue(Events::class.java)
                    evento?.let {
                        val eventoConId = it.copy(eventid = child.key)
                        eventos.add(eventoConId)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error al cargar eventos", Toast.LENGTH_SHORT).show()
            }
        })
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SectionTitle("Eventos")
        if (eventos.isEmpty()) {
            Text("No hay eventos disponibles", modifier = Modifier.padding(16.dp))
        } else {
            eventos.filter { it.upcoming }.forEach { event ->
                CardInfo(event = event, navController = navController)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun NoticeBody(context: Context, navController: NavController) {
    // Utilizamos remember y mutableStateListOf para almacenar el estado de las noticias
    val noticias = remember { mutableStateListOf<Notice>() }

    LaunchedEffect(Unit) {
        val ref = ConnectFirebase().child("noticias")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                noticias.clear() // Limpiar la lista antes de agregar nuevos elementos
                snapshot.children.forEach { child ->
                    val noticia = child.getValue(Notice::class.java)
                    noticia?.let {
                        noticias.add(it.copy(noticeid = child.key)) // Agregar la noticia a la lista
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error al cargar noticias", Toast.LENGTH_SHORT).show()
            }
        })
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SectionTitle("Noticias")
        if (noticias.isEmpty()) {
            Text("No hay noticias disponibles", modifier = Modifier.padding(16.dp))
        } else {
            noticias.forEach { notice ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    NoticeCard(notice = notice, navController = navController)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}


