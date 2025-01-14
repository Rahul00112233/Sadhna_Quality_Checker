package com.example.sadhnaqualitychecker.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sadhnaqualitychecker.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ISKCONPracticePage(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Sadhna Quality Calculator", fontWeight = FontWeight.SemiBold, color = Color.White)
                        },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}, colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Orange)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Chanting Section
            item {
                SectionTitle(title = "Chanting (Japa)")
                SectionItem("Prayerful Mood (20 points)", "Emphasizing devotion and sincerity in chanting. The more devotional and heartfelt the chanting, the more potent it becomes.")
                SectionItem("Attentiveness (25 points)", "Focus is key to effective chanting. Be mindful of every syllable and mantra to achieve the highest spiritual benefit.")
                SectionItem("Sleepiness (-10 points)", "Sleepiness detracts from the quality of chanting, reducing its effectiveness and connection with the Holy Name.")
                SectionItem("Sitting Japa (15 points)", "Sitting in a proper posture shows discipline and focus, enhancing the quality of chanting.")
                SectionItem("Back Straight (10 points)", "A straight back aids concentration and proper breathing, further enriching the chanting experience.")
                SectionItem("Multi-tasking Japa (-15 points)", "Attempting to multitask while chanting indicates a lack of focus and devotion, reducing the spiritual quality.")
                SectionItem("Vaishnava Aparadh (-20 points)", "Offenses against devotees severely detract from the benefits of chanting.")
                SectionItem("Naam Aparadh (-30 points)", "Offenses against the holy name are very serious and significantly reduce the potency of the chanting.")
                SectionItem("Morning Rounds (30 points)", "Morning chanting (during Brahma Muhurta) is highly auspicious and provides the maximum benefit for spiritual growth.")
                SectionItem("Evening Rounds (10 points)", "Evening chanting is beneficial but less potent than morning chanting.")
            }

            // Hearing Section
            item {
                SectionTitle(title = "Hearing (Shravan)")
                SectionItem("Made Notes (20 points)", "Taking notes during lectures or discussions shows a sincere effort to absorb and reflect on the teachings.")
                SectionItem("Notes Quality (20 points)", "The quality and clarity of the notes indicate the depth of understanding.")
                SectionItem("Attentiveness (15 points)", "Listening attentively to lectures ensures that you grasp the teachings and can later apply them.")
                SectionItem("Reciprocation (10 points)", "Actively engaging with the speaker shows understanding and involvement.")
                SectionItem("Multitasking (-10 points)", "Dividing attention between multiple tasks while hearing reduces the effectiveness of learning.")
                SectionItem("Asked Question (10 points)", "Asking thoughtful questions demonstrates active participation.")
                SectionItem("Relevancy of Asked Question (10 points)", "Relevant questions show engagement and comprehension.")
                SectionItem("Drawn Application Points (15 points)", "Points or lessons that are directly applicable to real life show practical engagement.")
            }

            // Reading Section
            item {
                SectionTitle(title = "Reading (Study)")
                SectionItem("Prescribed Book (20 points)", "Regularly reading prescribed books deepens one’s understanding of spiritual texts.")
                SectionItem("Made Notes (15 points)", "Taking notes from the reading material shows active engagement.")
                SectionItem("Notes Quality (20 points)", "High-quality notes reflect deep understanding.")
                SectionItem("Application Points (15 points)", "Extracting lessons from books for application in daily life is valuable.")
                SectionItem("Concentration (20 points)", "Deep concentration during reading ensures absorption of teachings.")
                SectionItem("Read Krishna Book Before Sleep (10 points)", "Ending the day with spiritual wisdom is a powerful practice.")
            }

            // Seva Section
            item {
                SectionTitle(title = "Seva (Service)")
                SectionItem("Productivity (30 points)", "Efficient and productive service pleases Krishna and fosters growth.")
                SectionItem("Serving Mode (70 points)", "Serving with love, humility, and devotion brings the greatest spiritual benefit.")
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun SectionItem(title: String, description: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
