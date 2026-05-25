package com.example.store.feature.store.presetation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store.R
import com.example.store.feature.store.presetation.component.icons


@Composable
fun LoginScreen(modifier: Modifier = Modifier, onClick: () -> Unit){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)


    ) {

       LoginCabecalho()

       Spacer(modifier = Modifier.height(32.dp))

       LoginForm()

       Spacer(modifier = Modifier.height(30.dp))

       LoginButtons(onClick = onClick)

        Spacer(modifier = Modifier.height(16.dp))


        icons(
        )
    }
}

@Composable
private fun LoginCabecalho(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.lg),
            contentDescription = "Logo da loja",
            modifier = Modifier.size(150.dp)
        )

        Text(
           "Create you account",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )

    }
}

@Composable
private fun LoginForm(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            state = rememberTextFieldState(),
            label = { Text("User") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.colors(
                //cor do texto
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,

                //cor do user e pass

                focusedLabelColor = Color(0xFF9E9E9E),
                unfocusedLabelColor = Color(0xFF9E9E9E),

                //fundo do label
                focusedContainerColor = Color(0xFF1F222A),
                unfocusedContainerColor = Color(0xFF1F222A),

                //cor da borda
                focusedIndicatorColor = Color(0xFF8A2BE2),
                unfocusedIndicatorColor = Color(0xFF8A2BE2)


            )
        )

        OutlinedTextField(
            state = rememberTextFieldState(),
            label = { Text("Password") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.colors(
                //cor do texto
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,

                //cor do user e pass

                focusedLabelColor = Color(0xFF9E9E9E),
                unfocusedLabelColor = Color(0xFF9E9E9E),

                //fundo do label
                focusedContainerColor = Color(0xFF1F222A),
                unfocusedContainerColor = Color(0xFF1F222A),

                //cor da borda
                focusedIndicatorColor = Color(0xFF8A2BE2),
                unfocusedIndicatorColor = Color(0xFF8A2BE2)


            )
        )
    }
}

@Composable
private fun LoginButtons(onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorIcon, contentColor = Color.White)
    ) { Text(
        "Sing up",
        fontSize = 18.sp
    )
    }
}
val colorIcon = Color(0xFF8A2BE2)
val colortext = Color(0xFF9E9E9E)
val colorlabel = Color(0xFF1F222A)