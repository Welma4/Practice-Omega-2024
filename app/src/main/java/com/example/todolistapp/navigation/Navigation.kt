package com.example.todolistapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.MainViewModel
import com.example.todolistapp.model.Project
import com.example.todolistapp.model.Task
import com.example.todolistapp.ui.screens.CreateTaskScreen
import com.example.todolistapp.ui.screens.MainScreen
import com.example.todolistapp.ui.screens.FoldersScreen
import com.example.todolistapp.ui.screens.ProjectTasksScreen
import com.example.todolistapp.ui.screens.EditTaskScreen

@Composable
fun MyApp(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {
    val tasksList = mainViewModel.tasksList.collectAsState(initial = emptyList())
    val projectsList = mainViewModel.projectsList.collectAsState(initial = emptyList())

    val navController = rememberNavController()
    val selectedTask = remember { mutableStateOf<Task?>(null) }

    fun onTaskCardClicked(task: Task) {
        selectedTask.value = task
        navController.navigate("EditTaskScreen")
    }

    fun addProject(project: Project) {
        mainViewModel.insertProject(project)
    }

    fun onTaskUpdated(updatedTask: Task) {
        mainViewModel.updateTask(updatedTask)
    }

    NavHost(navController, startDestination = "MainScreen") {
        composable("MainScreen") {
            MainScreen(navController, tasksList, ::onTaskCardClicked)
        }
        composable("CreateTaskScreen") {
            CreateTaskScreen(navController, tasksList, projectsList.value.toMutableStateList(), ::addProject)
        }
        composable("FoldersScreen") {
            FoldersScreen(navController, projectsList, tasksList)
        }
        composable("ProjectTasksScreen/{project}") { backStackEntry ->
            val project = backStackEntry.arguments?.getString("project") ?: ""
            ProjectTasksScreen(navController, project, tasksList, ::onTaskCardClicked)
        }
        composable("EditTaskScreen") {
            EditTaskScreen(navController, tasksList, selectedTask.value, ::onTaskUpdated)
        }
    }
}