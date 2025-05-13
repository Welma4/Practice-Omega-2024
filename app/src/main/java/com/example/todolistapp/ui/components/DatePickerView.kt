package com.example.todolistapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerView(selectedDate: String, onDateSelected: (String) -> Unit) {
    val dateState = rememberDatePickerState()
    var showCalendar by remember { mutableStateOf(false) }
    val currentDate = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(Date())


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
            .padding(bottom = 60.dp)
            .border(width = 1.dp, color = Color(0xFFDCE1EF), shape = RoundedCornerShape(20.dp)),
    ) {
        TextButton(
            onClick = { showCalendar = true },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    selectedDate,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFF6E7591)
                    ),
                    modifier = Modifier.weight(1f)
                )
                Image(
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = "arrow_down",
                    modifier = Modifier
                        .size(width = 15.dp, height = 15.dp)
                )
            }
        }
        if (showCalendar) {
            AlertDialog(
                onDismissRequest = { showCalendar = false },
                title = { Text("Select date") },
                text = {
                    DatePicker(
                        state = dateState,
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        showCalendar = false
                        val sdf = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)
                        val newSelectedDate = sdf.format(dateState.selectedDateMillis)
                        onDateSelected(newSelectedDate)
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = { showCalendar = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}