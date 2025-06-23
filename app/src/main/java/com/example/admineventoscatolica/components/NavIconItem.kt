package com.example.admineventoscatolica.components


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.admineventoscatolica.R

@Composable
fun NavIconItem(
    icon: Int,
    label: String,
    context: Context?,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val backgroundColor = if (isSelected) Color.LightGray.copy(alpha = 0.3f) else Color.Transparent

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(120.dp)
            .padding(5.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = label,
            modifier = Modifier
                .size(26.dp)
                .alpha(if (isSelected) 1f else 0.8f )
        )
        Text(
            text = label,
            color = if (isSelected) Color.White else Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.philosopher_bold)),
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}