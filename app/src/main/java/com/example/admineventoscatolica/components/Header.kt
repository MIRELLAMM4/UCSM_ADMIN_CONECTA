package com.example.admineventoscatolica.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.admineventoscatolica.R
import com.example.admineventoscatolica.states.NavItem

@Composable
fun Header(selectedItem: NavItem) {
    val headerText = when (selectedItem) {
        NavItem.EVENTOS -> "Explora los diferentes Eventos"
        NavItem.CONFERENCIAS -> "Explora las diferentes Conferencias"
        NavItem.NOTICIAS -> "Explora las diferentes Noticias"
        else -> "Explora el contenido"
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(R.mipmap.ucsm_image),
            contentDescription = "UCSM",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f/11f),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f/11f)
                .background(Color.Black.copy(alpha = 0.4f)) // Ajusta alpha (0.0 a 1.0)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.mipmap.ucsm_image_logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .height(60.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = headerText,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 38.sp,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = androidx.compose.ui.geometry.Offset(5f, 5f),
                            blurRadius = 0f
                        ),
                    ),
                    fontFamily = FontFamily(Font(R.font.lilitaone_regular)),
                    modifier = Modifier.padding(16.dp).fillMaxWidth()
                )
            }
        }
    }
}