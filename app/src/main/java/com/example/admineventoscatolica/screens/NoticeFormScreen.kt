
package com.example.admineventoscatolica.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.admineventoscatolica.model.Notice
import com.example.admineventoscatolica.services.addNotice
import com.example.admineventoscatolica.services.updateNotice
import com.example.admineventoscatolica.states.NavItem
import com.example.admineventoscatolica.ui.theme.VerdeSecundario

@Composable
fun NoticeFormScreen(
    navController: NavController,
    existingNotice: Notice? = null,
            onNoticeSaved: () -> Unit
) {
    val context = LocalContext.current

    var title by remember { mutableStateOf(existingNotice?.title ?: "") }
    var description by remember { mutableStateOf(existingNotice?.description ?: "") }
    var date by remember { mutableStateOf(existingNotice?.date ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
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
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val notice = Notice(
                    noticeid = existingNotice?.noticeid,
                    title = title,
                    description = description,
                    date = date,
                    image = null, // Si luego se agrega imagen, se reemplaza aquí
                    isUpcoming = true
                )
                if (notice.noticeid == null) {
                    addNotice(notice)
                    Toast.makeText(context, "Noticia creada", Toast.LENGTH_SHORT).show()
                } else {
                    updateNotice(notice)
                    Toast.makeText(context, "Noticia actualizada", Toast.LENGTH_SHORT).show()
                }
                navController.currentBackStackEntry?.savedStateHandle?.set("selectedNavItem", NavItem.NOTICIAS)
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(containerColor = VerdeSecundario),
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(if (existingNotice == null) "Crear" else "Actualizar")
        }
    }
}
