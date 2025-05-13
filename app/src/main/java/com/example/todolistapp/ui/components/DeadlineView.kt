package com.example.todolistapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.ui.components.DatePickerView

@Composable
fun DeadlineView(selectedDate: String, onDateSelected: (String) -> Unit) {
    Text(
        modifier = Modifier.padding(bottom = 5.dp),
        text = "Deadline",
        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D101C))
    )
    DatePickerView(selectedDate = selectedDate, onDateSelected = onDateSelected)
}