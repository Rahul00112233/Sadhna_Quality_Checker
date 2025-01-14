package com.example.sadhnaqualitychecker.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sadhnaqualitychecker.ui.theme.High
import com.example.sadhnaqualitychecker.ui.theme.Low
import com.example.sadhnaqualitychecker.ui.theme.Medium
import com.example.sadhnaqualitychecker.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SevaScreen(navController: NavController){
    var productivity by remember { mutableStateOf(0f) }
    var servingMode by remember { mutableStateOf("Duty") }

    val weights = mapOf(
        "Productivity" to 30,
        "Serving Mode" to 70
    )

    val totalScore = calculateSevaScore(
        productivity = productivity,
        servingMode = servingMode,
        weights = weights
    )
    var state =  rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Seva Quality Calculator", fontWeight = FontWeight.SemiBold, color = Color.White)
            },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}, colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Orange))
        }
    ){

        Column(modifier = Modifier.fillMaxSize().padding(it).padding(horizontal = 12.dp).verticalScroll(state)) {
            SliderWithLabel(
                label = "Productivity",
                value = productivity,
                onValueChange = { productivity = it }
            )

            DropdownMenuWithLabel(
                label = "Serving Mood",
                selectedValue = servingMode,
                onValueChange = { servingMode = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Calculated Percentage: ${totalScore}%",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = when {
                    totalScore <= 50 -> Low
                    totalScore in 51..75 -> Medium
                    else -> High
                }
            )

        }
    }
}

fun calculateSevaScore(
    productivity: Float,
    servingMode: String,
    weights: Map<String, Int>
): Int {
    val maxScore = weights.values.filter { it > 0 }.sum()
    var totalScore = 0f

    totalScore += productivity * (weights["Productivity"] ?: 0)
    // Adjust score based on the serving mode
    val modeAdjustment = when (servingMode) {
        "Forced" -> 0.3f
        "Duty" -> 0.6f
        "Opportunity to please Lord Krsna" -> 1.0f
        else -> 0.0f
    }
    totalScore += modeAdjustment * (weights["Serving Mode"] ?: 0)

    // Normalize to percentage
    return ((totalScore / maxScore) * 100).toInt().coerceIn(0, 100)
}

