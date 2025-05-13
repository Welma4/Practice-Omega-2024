package com.example.todolistapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.model.Task

@Composable
fun TodayTasksView(
    tasks: State<List<Task>>,
    onTaskClicked: (Task) -> Unit
) {
    val selectedButton = remember { mutableStateOf("All tasks") }

    Column {

        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Today tasks",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D101C))
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(Color.White),
        ) {

            Row(modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    modifier = Modifier.size(width = 115.dp, height = 33.dp),
                    onClick = { selectedButton.value = "All tasks" },
                    colors = ButtonDefaults.buttonColors(containerColor = if (selectedButton.value == "All tasks") Color(0xFF613BE7) else Color(0xFFFFFFFF))
                ) {
                    Text(text = "All tasks",
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = if (selectedButton.value == "All tasks") Color(0xFFFFFFFF) else Color(0xFF6E7591))
                    )
                }
                Button(
                    modifier = Modifier.size(width = 115.dp, height = 33.dp),
                    onClick = { selectedButton.value = "In progress" },
                    colors = ButtonDefaults.buttonColors(containerColor = if (selectedButton.value == "In progress") Color(0xFF613BE7) else Color(0xFFFFFFFF))
                ) {
                    Text(text = "In progress",
                        style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold, color =  if (selectedButton.value == "In progress") Color(0xFFFFFFFF) else Color(0xFF6E7591))
                    )
                }
                Button(
                    modifier = Modifier.size(width = 115.dp, height = 33.dp),
                    onClick = { selectedButton.value = "Completed" },
                    colors = ButtonDefaults.buttonColors(containerColor = if (selectedButton.value == "Completed") Color(0xFF613BE7) else Color(0xFFFFFFFF))
                ) {
                    Text(text = "Completed",
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color =  if (selectedButton.value == "Completed") Color(0xFFFFFFFF) else Color(0xFF6E7591))
                    )
                }
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()

        ) {
            val filteredTasks = when (selectedButton.value) {
                "In progress" -> tasks.value.filter { !it.completed }.reversed()
                "Completed" -> tasks.value.filter { it.completed }.reversed()
                else -> tasks.value.reversed()
            }
            items(filteredTasks) { task ->
                TaskCard(task = task, onTaskCardClicked = onTaskClicked)
            }
        }
    }
}