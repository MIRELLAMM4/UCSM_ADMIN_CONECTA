package com.example.admineventoscatolica.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.admineventoscatolica.R

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    textFieldColors: TextFieldColors,
) {
    val ArrobaIcon = painterResource(R.mipmap.arroba)
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(label) },
        singleLine = true,
        leadingIcon = {
            IconButton(
                onClick = {}
            ) {
                Image(
                    painter = ArrobaIcon,
                    contentDescription = "Icono de correo",
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = textFieldColors,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun PasswordTextField(
    value: String,
    passwordVisible: Boolean,
    onValueChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    label: String,
    textFieldColors: TextFieldColors,
) {
    // Iconos para los campos de texto
    val EyeIcon = painterResource(R.mipmap.eye_icon)
    val ClosedEyeIcon = painterResource(R.mipmap.closedeyes_icon)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = { Text(label) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            val image = if (passwordVisible) EyeIcon else ClosedEyeIcon
            val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"

            IconButton(onClick = onPasswordVisibilityChange) {
                Image(
                    painter = image,
                    contentDescription = description,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = textFieldColors,

        modifier = Modifier.fillMaxWidth()
    )
}