package com.example.todolistapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project_table")
data class Project(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String
)