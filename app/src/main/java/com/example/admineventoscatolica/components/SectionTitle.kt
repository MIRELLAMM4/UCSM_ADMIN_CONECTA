package com.example.admineventoscatolica.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.admineventoscatolica.R
import com.example.admineventoscatolica.ui.theme.VerdeSecundario


@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = VerdeSecundario,
        fontFamily = FontFamily(Font(resId = R.font.philosopher_bold)),
        modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth(),
    )
}