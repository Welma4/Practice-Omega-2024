package com.example.todolistapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.todolistapp.model.Task
import com.example.todolistapp.ui.components.DeadlineView
import com.example.todolistapp.ui.components.TaskDescriptionView
import com.example.todolistapp.ui.components.TitleTaskView

@Composable
fun EditTaskScreen(
    navController: NavController,
    tasks: State<List<Task>>,
    selectedTask: Task?,
    onTaskUpdated: (Task) -> Unit,
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {
    fun onDeleteTask(navController: NavController, tasks: State<List<Task>>, selectedTask: Task) {
        mainViewModel.deleteTask(selectedTask)
        navController.navigate("MainScreen")
    }

    if (selectedTask != null) {
        var taskTitle = remember { mutableStateOf(selectedTask.title) }
        var taskDescription = remember { mutableStateOf(selectedTask.description) }
        var selectedDate = remember { mutableStateOf(selectedTask.deadline) }
        var selectedProject = remember { mutableStateOf(selectedTask.project) }
        var isCompleted = remember { mutableStateOf(selectedTask.completed) }

        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFFDFE4F1), Color(0xFFFFFFFF))
                    )
                )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
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
                    text = "Edit task",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D101C)),
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { onDeleteTask(navController, tasks, selectedTask) }, modifier = Modifier.background(
                    Color.Transparent)) {
                    Image(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = "Delete task"
                    )
                }
            }


            Column(modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(50.dp))
                .background(Color.White)
                .border(width = 1.dp, color = Color(0xFFDCE1EF), shape = RoundedCornerShape(50.dp))
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 35.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .padding(end = 3.dp)
                                .size(22.dp)
                                .clip(CircleShape)
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFDCE1EF),
                                    shape = CircleShape
                                )
                        ) {
                            Checkbox(
                                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF613BE7), uncheckedColor = Color.White),
                                checked = !isCompleted.value,
                                onCheckedChange = { isCompleted.value = !it }
                            )
                        }
                        Text(
                            text = "In Progress",
                            style = TextStyle(fontSize = 16.sp),
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .padding(end = 3.dp)
                                .size(22.dp)
                                .clip(CircleShape)
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFDCE1EF),
                                    shape = CircleShape
                                )
                        ) {
                            Checkbox(
                                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF613BE7), uncheckedColor = Color.White),
                                checked = isCompleted.value,
                                onCheckedChange = { isCompleted.value = it }
                            )
                        }
                        Text(
                            text = "Completed",
                            style = TextStyle(fontSize = 16.sp),
                        )
                    }
                }

                Column(modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)) {
                    Column(modifier = Modifier.padding(bottom = 150.dp)) {
                        TitleTaskView(taskTitle.value, onTaskTitleChange = { taskTitle.value = it })
                        TaskDescriptionView(taskDescription.value, onTaskDescriptionChange = { taskDescription.value = it })
                        DeadlineView(selectedDate = selectedDate.value, onDateSelected = { selectedDate.value = it })
                    }

                    SaveChangesButton(
                        navController = navController,
                        selectedTask = selectedTask,
                        taskTitle = taskTitle.value,
                        taskDescription = taskDescription.value,
                        selectedProject = selectedProject.value,
                        selectedDate = selectedDate.value,
                        onTaskUpdated = {
                            mainViewModel.updateTask(it)
                            onTaskUpdated(it)
                        },
                        isCompleted = isCompleted.value
                    )
                }
            }
        }
    }
}

@Composable
fun SaveChangesButton(
    navController: NavController,
    selectedTask: Task,
    taskTitle: String,
    taskDescription: String,
    selectedProject: String,
    selectedDate: String,
    onTaskUpdated: (Task) -> Unit,
    isCompleted: Boolean
) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF613BE7)),
        modifier = Modifier.size(width = 400.dp, height = 50.dp),
        onClick = {
            val updatedTask = selectedTask.copy(
                title = taskTitle,
                description = taskDescription,
                project = selectedProject,
                deadline = selectedDate,
                completed = isCompleted
            )
            onTaskUpdated(updatedTask)
            navController.navigate("MainScreen")
        }
    ) {
        Text(
            "Save changes",
            style = TextStyle(fontSize = 22.sp, color = Color(0xFFFFFFFF))
        )
    }
}

