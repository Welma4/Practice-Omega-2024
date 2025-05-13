package com.example.todolistapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolistapp.R
import com.example.todolistapp.model.Task
import com.example.todolistapp.ui.components.BottomNavigationView
import com.example.todolistapp.ui.components.TaskCard

@Composable
fun ProjectTasksScreen(
    navController: NavController,
    project: String,
    tasks: State<List<Task>>,
    onTaskCardClicked: (Task) -> Unit
) {
    Scaffold(
        bottomBar = { BottomNavigationView(navController) },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFDFE4F1), Color(0xFFFFFFFF)),
                        )
                    )
            ) {
                Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                    Row(modifier = Modifier.padding(top = 5.dp)) {
                        IconButton(onClick = { navController.navigate("FoldersScreen") }, modifier = Modifier.background(
                            Color.Transparent)) {
                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.arrow_left),
                                contentDescription = "FolderScreen"
                            )
                        }
                        Text(
                            modifier = Modifier.padding(top = 12.dp),
                            text = "Tasks in $project",
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D101C))
                        )
                    }

                    LazyColumn(modifier = Modifier.padding(top = 10.dp)) {
                        items(tasks.value.filter { it.project == project }) { task ->
                            TaskCard(task = task, onTaskCardClicked = onTaskCardClicked)
                        }
                    }
                }
            }
        }
    )
}