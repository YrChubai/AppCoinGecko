package com.example.appcoingecko.ui.screen.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.appcoingecko.R
import com.example.appcoingecko.ui.theme.DominantOrange

@Composable
fun ErrorRefresher(
    onRetry: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image: Painter = painterResource(id = R.drawable.bitcoin)
        Image(painter = image,contentDescription = "logo")
        Text(
            text = "Произошла какая-то ошибка :(\n" +
                    "Попробуем снова?",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = DominantOrange),
            modifier = Modifier
                .padding(16.dp)
            ,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Попробовать", color = Color.White)
        }
    }
}