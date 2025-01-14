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
fun Reading(navController: NavController){
    var prescribedBook by remember { mutableStateOf(false) }
    var madeNotes by remember { mutableStateOf(0f) }
    var notesQuality by remember { mutableStateOf(0f) }
    var applicationPoints by remember { mutableStateOf(false) }
    var concentration by remember { mutableStateOf(0f) }
    var readKrishnaBookBeforeSleep by remember { mutableStateOf(false) }

    val weights = mapOf(
        "Prescribed Book" to 20,
        "Made Notes" to 15,
        "Notes Quality" to 20,
        "Application Points" to 15,
        "Concentration" to 20,
        "Read Krishna Book Before Sleep" to 10
    )

    val totalScore = calculateReadingScore(
        prescribedBook = prescribedBook,
        madeNotes = madeNotes,
        notesQuality = notesQuality,
        applicationPoints = applicationPoints,
        concentration = concentration,
        readKrishnaBookBeforeSleep = readKrishnaBookBeforeSleep,
        weights = weights
    )
    var state =  rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Reading Quality Calculator", fontWeight = FontWeight.SemiBold, color = Color.White)
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
            CheckboxWithLabel(
                label = "Prescribed/Permitted Book?",
                isChecked = prescribedBook,
                onCheckedChange = { prescribedBook = it }
            )
            if(prescribedBook){
                SliderWithLabel(
                    label = "Made Notes",
                    value = madeNotes,
                    onValueChange = { madeNotes = it }
                )
            }
            if(prescribedBook){
                SliderWithLabel(
                    label = "Notes Quality",
                    value = notesQuality,
                    onValueChange = { notesQuality = it }
                )
            }
            if(prescribedBook){
                CheckboxWithLabel(
                    label = "Drawn Application Points?",
                    isChecked = applicationPoints,
                    onCheckedChange = { applicationPoints = it }
                )
            }
            if(prescribedBook){
                SliderWithLabel(
                    label = "Concentration Level",
                    value = concentration,
                    onValueChange = { concentration = it }
                )
            }
            CheckboxWithLabel(
                label = "Read Krishna Book Before Sleep?",
                isChecked = readKrishnaBookBeforeSleep,
                onCheckedChange = { readKrishnaBookBeforeSleep = it }
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

fun calculateReadingScore(
    prescribedBook: Boolean,
    madeNotes: Float,
    notesQuality: Float,
    applicationPoints: Boolean,
    concentration: Float,
    readKrishnaBookBeforeSleep: Boolean,
    weights: Map<String, Int>
): Int {
    val maxScore = weights.values.filter { it > 0 }.sum() // Sum of positive weights
    var totalScore = 0f

    if (prescribedBook) totalScore += weights["Prescribed Book"] ?: 0
    if(prescribedBook) {
        totalScore += madeNotes * (weights["Made Notes"] ?: 0)
        totalScore += notesQuality * (weights["Notes Quality"] ?: 0)
        if (applicationPoints) totalScore += weights["Application Points"] ?: 0
        totalScore += concentration * (weights["Concentration"] ?: 0)
    }else{
        totalScore += totalScore+0
    }
    if (readKrishnaBookBeforeSleep) totalScore += weights["Read Krishna Book Before Sleep"] ?: 0

    return ((totalScore / maxScore) * 100).toInt().coerceIn(0, 100)
}
