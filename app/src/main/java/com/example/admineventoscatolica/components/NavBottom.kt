package com.example.admineventoscatolica.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.admineventoscatolica.R
import com.example.admineventoscatolica.states.NavItem
import com.example.admineventoscatolica.ui.theme.VerdeSecundario

@Composable
fun NavBottom(
    context: Context?,
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(color = VerdeSecundario),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NavIconItem(
            icon = R.mipmap.events_logo,
            label = "Eventos",
            context = context,
            isSelected = selectedItem == NavItem.EVENTOS,
            onClick = { onItemSelected(NavItem.EVENTOS) }
        )
        NavIconItem(
            icon = R.mipmap.conference_logo,
            label = "Conferencias",
            context = context,
            isSelected = selectedItem == NavItem.CONFERENCIAS,
            onClick = { onItemSelected(NavItem.CONFERENCIAS) }
        )
        NavIconItem(
            icon = R.mipmap.notice_logo,
            label = "Noticias",
            context = context,
            isSelected = selectedItem == NavItem.NOTICIAS,
            onClick = { onItemSelected(NavItem.NOTICIAS) }
        )
    }
}
