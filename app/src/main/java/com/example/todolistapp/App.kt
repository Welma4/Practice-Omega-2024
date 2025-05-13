package com.example.todolistapp

import android.app.Application
import com.example.todolistapp.model.TasksDb

class App : Application() {
    val database by lazy { TasksDb.createDataBase(this) }
}