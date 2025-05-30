package com.akshay.todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todo")
data class ToDoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val createdAt: Date
)