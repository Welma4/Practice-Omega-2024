package com.example.todolistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.todolistapp.model.Project
import com.example.todolistapp.model.Task
import com.example.todolistapp.model.TasksDb
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val database: TasksDb) : ViewModel() {
    val tasksList = database.taskDao.getAllTasks()
    val projectsList = database.projectDao.getAllProjects().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertTask(task: Task) {
        viewModelScope.launch {
            database.taskDao.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            database.taskDao.insertTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            database.taskDao.deleteTask(task)
        }
    }

    fun insertProject(project: Project) {
        viewModelScope.launch {
            database.projectDao.insertProject(project)
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>, extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return MainViewModel(database) as T
            }
        }
    }
}