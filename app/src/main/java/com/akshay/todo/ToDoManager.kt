package com.akshay.todo

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.Date

object ToDoManager {

    private val toDoList = mutableListOf<ToDoModel>()

    fun getAllToDos(): List<ToDoModel> {
        return toDoList
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addToDo(title: String) {
        val newToDo = ToDoModel(
            id = System.currentTimeMillis().toInt(),
            title = title,
            createdAt = Date.from(Instant.now())
        )
        toDoList.add(newToDo)
    }

    fun deleteToDo(id: Int) {
        toDoList.removeIf { it.id == id }
    }
}