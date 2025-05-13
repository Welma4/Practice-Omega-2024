package com.example.todolistapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolistapp.model.Task
import com.example.todolistapp.ui.components.BottomNavigationView
import com.example.todolistapp.ui.components.CreateButtonView
import com.example.todolistapp.ui.components.SummaryView
import com.example.todolistapp.ui.components.TodayTasksView
import com.example.todolistapp.ui.components.WelcomeMenu

@Composable
fun MainScreen(
    navController: NavController,
    tasks: State<List<Task>>,
    onTaskCardClicked: (Task) -> Unit,
) {
    Scaffold(
        bottomBar = { BottomNavigationView(navController) },
        content = { innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFFFFFFF), Color(0xFFDFE4F1)),
                    )
                )) {
                Column(modifier = Modifier
                    .padding(innerPadding)
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 30.dp)
                ) {
                    WelcomeMenu()
                    SummaryView(tasks)
                    CreateButtonView(navContr = navController, screen = "CreateTaskScreen")
                    TodayTasksView(tasks, onTaskCardClicked)
                }
            }
        }
    )
}