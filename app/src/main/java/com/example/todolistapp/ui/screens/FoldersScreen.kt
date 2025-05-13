package com.example.todolistapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolistapp.MainViewModel
import com.example.todolistapp.R
import com.example.todolistapp.model.Project
import com.example.todolistapp.model.Task
import com.example.todolistapp.ui.components.BottomNavigationView
import com.example.todolistapp.ui.components.ProjectCard

@Composable
fun FoldersScreen(
    navController: NavController,
    projects: State<List<Project>>,
    tasks: State<List<Task>>,
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {
    var selectedProject by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            BottomNavigationView(navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf( Color(0xFFFFFFFF), Color(0xFFDFE4F1))
                    )
                )
                .padding(innerPadding)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 30.dp)
                    .background(Color.Transparent)
            ) {
                IconButton(onClick = { navController.navigate("MainScreen") }) {
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = "Home page"
                    )
                }

                Text(
                    text = "Your projects",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D101C))
                )
            }
        }

        Box(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 20.dp)) {
            LazyColumn(modifier = Modifier.padding(top = 50.dp)) {
                items(projects.value) { project ->
                    ProjectCard(project.name, navController)
                }
            }
        }
    }
}