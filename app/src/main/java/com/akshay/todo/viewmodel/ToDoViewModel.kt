package com.akshay.todo.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshay.todo.MainApplication
import com.akshay.todo.model.ToDoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class ToDoViewModel : ViewModel() {

    val toDoDao = MainApplication.Companion.toDoDatabase.getToDoDao()

    val toDoList: LiveData<List<ToDoModel>>
        get() = toDoDao.getAllToDos()

    @RequiresApi(Build.VERSION_CODES.O)
    fun addToDo(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoDao.addToDo(ToDoModel(title = title, createdAt = Date.from(Instant.now())))
        }
    }

    fun deleteToDo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoDao.deleteToDo(id)
        }
    }

    fun updateToDo(item: ToDoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoDao.updateToDo(item)
        }
    }
}