package com.example.admineventoscatolica.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import com.example.admineventoscatolica.R
import com.example.admineventoscatolica.ui.theme.PrimaryColor
import com.example.eventoscatolica.services.LoginService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun BodyLogin(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val READEX_PRO_BOLD = FontFamily(
        Font(R.font.readexprobold)
    ) // Fuente Readex Pro Bold

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.Black)
    ) {
        HeaderBodyLogin()
        Spacer(modifier = Modifier.height(20.dp)) // Espacio entre el encabezado y el cuerpo
        SubtitleBody(
            subtitle = "XXXI Jornada Internacional de Ingeniería de Sistemas",
            fontFamily = READEX_PRO_BOLD,
            fontSize = 18.sp
        ) // Título de la sección
        Spacer(modifier = Modifier.height(20.dp))
        Login(
            readexProBold = READEX_PRO_BOLD,
            onLoginSuccess = { onLoginSuccess() },
            onNavigateToRegister = onNavigateToRegister
        )
    }
}

@Composable
fun HeaderBodyLogin() {
    val logoJinis = painterResource(R.mipmap.logo_jinis_2024)
    Box(
        modifier = Modifier
            .height(164.dp)
            .fillMaxWidth()
            .background(color = Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = logoJinis,
            contentDescription = "Logo JINIS",
            modifier = Modifier
                .fillMaxWidth(1f) // 90% del ancho de la pantalla
                .fillMaxHeight(),
            contentScale = ContentScale.Fit // Ajusta la imagen al espacio disponible
        )
    }
}

@Composable
fun Login(
    readexProBold: FontFamily,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    // Variables para almacenar el estado de los campos de texto
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // TextFields para el inicio de sesión
    val textFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = PrimaryColor,
        unfocusedIndicatorColor = Color.Transparent,
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        cursorColor = PrimaryColor,
        focusedLabelColor = Color.Black,
        unfocusedLabelColor = Color.Black
    )

    Column(
        modifier = Modifier
            .fillMaxHeight(0.75f)
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp)) // <- recorta el contenedor con esquinas redondeadas
            .background(Color.Black.copy(0.5f))
            .padding(20.dp), // Espacio alrededor de los campos de texto
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "INICIAR SESIÓN",
            fontFamily = readexProBold,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            color = PrimaryColor
        )

        Spacer(modifier = Modifier.height(20.dp)) // Espacio entre el título y los campos de texto

        EmailTextField(
            value = email,
            onValueChange = { email = it },
            label = "Correo Electrónico",
            textFieldColors = textFieldColors,
        )

        Spacer(modifier = Modifier.height(10.dp))

        PasswordTextField(
            value = password,
            onValueChange = { password = it },
            label = "Contraseña",
            textFieldColors = textFieldColors,
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomButton(
            onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    val result = LoginService.login(email, password)
                    if (result.isSuccess) {
                        onLoginSuccess()
                        Toast.makeText(context, "Bienvenido a la UCSM Conecta",
                            Toast.LENGTH_LONG).show()
                    } else {
                        // Mostrar error con Snackbar, Toast, etc.
                        Toast.makeText(context, "El usuario no existe",
                            Toast.LENGTH_LONG).show()
                    }
                }
            },
            text = "Ingresar"
        ) // Botón personalizado para iniciar sesión

        Text(
            text = "¿No tienes una cuenta? Regístrate",
            color = PrimaryColor,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 12.dp)
                .clickable {
                    onNavigateToRegister()
                },
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )

    }
}