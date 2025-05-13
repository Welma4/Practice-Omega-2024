package com.example.todolistapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.model.Task

@Composable
fun SummaryView(tasks: State<List<Task>>) {
    Text(
        modifier = Modifier.padding(bottom = 20.dp),
        text = "Summary",
        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D101C))
    )
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(end = 10.dp)
                .size(width = 160.dp, height = 80.dp)
                .weight(1f),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.Gray),
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SummaryItem(title = "Assigned tasks", count = tasks.value.size)
            }
        }
        Card(
            modifier = Modifier
                .size(width = 160.dp, height = 80.dp)
                .weight(1f),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.Gray),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SummaryItem(title = "Completed tasks", count = tasks.value.count { it.completed })
            }
        }
    }
}