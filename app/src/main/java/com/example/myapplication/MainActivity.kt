package com.example.myapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FlashCardApp()
                }
            }
        }
    }
}


@Composable
fun FlashCardApp() {
    // Sample list of study subjects
    val subjects = listOf(
        "Matemática",
        "Fisíca",
        "Ciências",
        "Biologia",
        "Historia",
        "Literatura",
        "Programação"
    )


    // State to track checked subjects
    var checkedSubjects by remember { mutableStateOf(setOf<String>()) }


    // Calculate progress (0f to 1f)
    val progress = if (subjects.isEmpty()) 0f else checkedSubjects.size.toFloat() / subjects.size


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "StudyCheck",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        // Circular progress bar
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier.size(150.dp),
            strokeWidth = 12.dp,
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )


        // Progress text
        Text(
            text = "${(progress * 100).toInt()}% Complete",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 16.dp)
        )


        // Subjects checklist
        Text(
            text = "Ckecklist:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
                .align(Alignment.Start)
        )


        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(subjects) { subject ->
                SubjectItem(
                    subject = subject,
                    isChecked = checkedSubjects.contains(subject),
                    onCheckedChange = { isChecked ->
                        checkedSubjects = if (isChecked) {
                            checkedSubjects + subject
                        } else {
                            checkedSubjects - subject
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun SubjectItem(
    subject: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                checkmarkColor = Color.White
            )
        )
        Text(
            text = subject,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun FlashCardAppPreview() {
    MyApplicationTheme {
        FlashCardApp()
    }
}
