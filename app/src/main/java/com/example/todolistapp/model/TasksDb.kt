package com.example.todolistapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Task::class,
        Project::class
    ],
    version = 2,
)
abstract class TasksDb : RoomDatabase(){
    abstract val taskDao: TaskDao
    abstract val projectDao: ProjectDao
    companion object{
        fun createDataBase(context: Context): TasksDb {
            return Room.databaseBuilder(
                context,
                TasksDb::class.java,
                "tasks.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

    }

}