package com.example.todolistapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolistapp.model.Task

@Composable
fun CreateTask(
    navController: NavController,
    tasks: State<List<Task>>,
    onCreateTask: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF613BE7)),
            modifier = Modifier.size(width = 400.dp, height = 50.dp),
            onClick = {
                onCreateTask()
                navController.navigate("MainScreen")
            }) {
            Text(
                "Create new task",
                style = TextStyle(fontSize = 22.sp, color = Color(0xFFFFFFFF))
            )
        }
    }
}