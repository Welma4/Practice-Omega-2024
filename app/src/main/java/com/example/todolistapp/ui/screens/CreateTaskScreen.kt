package com.example.todolistapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.todolistapp.ui.components.CreateTask
import com.example.todolistapp.ui.components.DeadlineView
import com.example.todolistapp.ui.components.ProjectNameView
import com.example.todolistapp.ui.components.TaskDescriptionView
import com.example.todolistapp.ui.components.TitleTaskView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CreateTaskScreen(
    navController: NavController,
    tasks: State<List<Task>>,
    projects: SnapshotStateList<Project>,
    addProject: (Project) -> Unit,
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    val currentDate = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date())
    var selectedDate by remember { mutableStateOf(currentDate) }
    var selectedProject by remember { mutableStateOf("Daily Routine") }

    var newProjectName by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.background(
            Brush.linearGradient(
                colors = listOf(Color(0xFFDFE4F1), Color(0xFFFFFFFF))
            )
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            IconButton(onClick = { navController.navigate("MainScreen") }, modifier = Modifier.background(
                Color.Transparent)) {
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "Home page"
                )
            }

            Text(
                text = "Create new task",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D101C))
            )
        }

        Column(modifier = Modifier
            .padding(top = 150.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(50.dp))
            .background(Color.White)
            .border(width = 1.dp, color = Color(0xFFDCE1EF), shape = RoundedCornerShape(50.dp))
        ) {
            Column(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)) {
                TitleTaskView(taskTitle, onTaskTitleChange = { taskTitle = it })
                TaskDescriptionView(taskDescription, onTaskDescriptionChange = { taskDescription = it })
                ProjectNameView(
                    projects = projects,
                    selectedProject = selectedProject,
                    onProjectSelected = { selectedProject = it },
                    newProjectName = newProjectName,
                    onNewProjectNameChange = { newProjectName = it },
                    onAddProject = {
                        if (newProjectName.isNotBlank()) {
                            val newProject = Project(name = newProjectName)
                            addProject(newProject)
                            selectedProject = newProjectName
                            newProjectName = ""
                        }
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it }
                )
                DeadlineView(selectedDate = selectedDate, onDateSelected = { selectedDate = it })
                CreateTask(navController = navController, tasks = tasks, onCreateTask = {
                    var newTaskTitle = taskTitle
                    if (taskTitle.isEmpty()) {
                        val taskCount = tasks.value.count { it.title.startsWith("New task") } + 1
                        newTaskTitle = "New task $taskCount"
                    }
                    val newTask = Task(
                        id = System.currentTimeMillis().toInt(),
                        newTaskTitle,
                        taskDescription,
                        selectedProject,
                        selectedDate,
                        false
                    )
                    mainViewModel.insertTask(newTask)
                    navController.navigate("MainScreen")
                })
            }
        }
    }
}