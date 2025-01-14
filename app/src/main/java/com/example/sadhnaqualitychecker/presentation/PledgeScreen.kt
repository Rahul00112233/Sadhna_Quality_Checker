package com.example.sadhnaqualitychecker.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sadhnaqualitychecker.ui.theme.Orange

@Composable
fun PledgeScreenWithTyping(navController: NavController) {
    val fullText = """
        "With a heart full of gratitude and humility, I pledge to truthfully and carefully provide all details regarding my chanting and devotional practices.
        
        I understand that Krishna is the ultimate witness to all my efforts and intentions. By being honest, I aim to purify my consciousness and strengthen my commitment to the holy name. 
        
        May this effort bring me closer to the lotus feet of Sri Sri Radha-Krishna and inspire me to serve Their divine mission with sincerity, enthusiasm, and devotion."
        
        Hare Krishna.
    """.trimIndent()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Pledge Text in the center
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Humble Pledge for Devotional Growth",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Orange,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = fullText,
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                color = Color.DarkGray
            )
        }

        Button(
            onClick = {
                navController.navigate("home")
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor= Orange)
        ) {
            Text("Next", color = Color.White, fontSize = 18.sp)
        }
    }
}
