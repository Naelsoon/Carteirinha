package com.example.store.feature.store.presetation.component

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun icons(){
    val colorIcon = Color(0xFF8A2BE2)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 1.dp,
                color = Color.Gray.copy(alpha = 0.5f)
            )

            Text(
                "Continue",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 1.dp,
                color = Color.Gray.copy(alpha = 0.5f)
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = com.example.store.R.drawable.facebook),
                contentDescription = "Facebook",
                modifier = Modifier.size(40.dp),
                tint = colorIcon
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                painter = painterResource(id = com.example.store.R.drawable.git),
                contentDescription = "Facebook",
                modifier = Modifier.size(40.dp),
                tint = colorIcon
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                painter = painterResource(id = com.example.store.R.drawable.lin),
                contentDescription = "Facebook",
                modifier = Modifier.size(40.dp),
                tint = colorIcon
            )
        }
    }
}