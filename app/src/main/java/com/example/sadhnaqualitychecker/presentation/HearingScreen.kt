package com.example.sadhnaqualitychecker.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sadhnaqualitychecker.ui.theme.High
import com.example.sadhnaqualitychecker.ui.theme.Low
import com.example.sadhnaqualitychecker.ui.theme.Medium
import com.example.sadhnaqualitychecker.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HearingScreen(navController: NavController){
    var madeNotes by remember { mutableStateOf(0f) }
    var notesQuality by remember { mutableStateOf(0f) }
    var attentiveness by remember { mutableStateOf(0f) }
    var reciprocation by remember { mutableStateOf(0f) }
    var multitasking by remember { mutableStateOf(false) }
    var askedQuestion by remember { mutableStateOf(false) }
    var relevancyOfAskedQuestion by remember { mutableStateOf(0f) }
    var drawnApplicationPoints by remember { mutableStateOf(false) }

    val weights = mapOf(
        "Made Notes" to 20,
        "Notes Quality" to 20,
        "Attentiveness" to 15,
        "Reciprocation" to 10,
        "Multitasking" to -10,
        "Asked Question" to 10,
        "Relevancy of Asked Question" to 10,
        "Drawn Application Points" to 15
    )

    // Calculate hearing score
    val totalScore = calculateHearingScore(
        madeNotes = madeNotes,
        notesQuality = notesQuality,
        attentiveness = attentiveness,
        reciprocation = reciprocation,
        multitasking = multitasking,
        askedQuestion = askedQuestion,
        relevancyOfAskedQuestion = relevancyOfAskedQuestion,
        drawnApplicationPoints = drawnApplicationPoints,
        weights = weights
    )
    var state =  rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Hearing Quality Calculator", fontWeight = FontWeight.SemiBold, color = Color.White)
            },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}, colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
                ,
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Orange))
        }
    ){

        Column(modifier = Modifier.fillMaxSize().padding(it).padding(horizontal = 12.dp).verticalScroll(state)) {
            SliderWithLabel(
                label = "Made Notes",
                value = madeNotes,
                onValueChange = { madeNotes = it }
            )
            SliderWithLabel(
                label = "Notes Quality",
                value = notesQuality,
                onValueChange = { notesQuality = it }
            )
            SliderWithLabel(
                label = "Attentiveness",
                value = attentiveness,
                onValueChange = { attentiveness = it }
            )
            SliderWithLabel(
                label = "Reciprocation",
                value = reciprocation,
                onValueChange = { reciprocation = it }
            )
            CheckboxWithLabel(
                label = "Multitasking",
                isChecked = multitasking,
                onCheckedChange = { multitasking = it }
            )
            CheckboxWithLabel(
                label = "Asked Question",
                isChecked = askedQuestion,
                onCheckedChange = { askedQuestion = it }
            )
            // Only show relevancy slider if a question was asked
            if (askedQuestion) {
                SliderWithLabel(
                    label = "Relevancy of Asked Question",
                    value = relevancyOfAskedQuestion,
                    onValueChange = { relevancyOfAskedQuestion = it }
                )
            }
            CheckboxWithLabel(
                label = "Drawn Application Points?",
                isChecked = drawnApplicationPoints,
                onCheckedChange = { drawnApplicationPoints = it }
            )

            Spacer(modifier = Modifier.height(8.dp))
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

fun calculateHearingScore(
    madeNotes: Float,
    notesQuality: Float,
    attentiveness: Float,
    reciprocation: Float,
    multitasking: Boolean,
    askedQuestion: Boolean,
    relevancyOfAskedQuestion: Float,
    drawnApplicationPoints: Boolean,
    weights: Map<String, Int>
): Int {
    val maxScore = weights.values.filter { it > 0 }.sum() // Sum of positive weights
    var totalScore = 0f

    totalScore += madeNotes * (weights["Made Notes"] ?: 0)
    totalScore += notesQuality * (weights["Notes Quality"] ?: 0)
    totalScore += attentiveness * (weights["Attentiveness"] ?: 0)
    totalScore += reciprocation * (weights["Reciprocation"] ?: 0)
    if (multitasking) totalScore += (weights["Multitasking"] ?: 0)
    if (askedQuestion) {
        totalScore += (weights["Asked Question"] ?: 0)
        // Add relevancy to score only if question is asked
        totalScore += relevancyOfAskedQuestion * (weights["Relevancy of Asked Question"] ?: 0)
    }
    if (drawnApplicationPoints) totalScore += (weights["Drawn Application Points"] ?: 0)

    // Normalize to percentage
    return ((totalScore / maxScore) * 100).toInt().coerceIn(0, 100)
}
