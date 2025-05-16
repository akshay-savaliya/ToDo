package com.akshay.todo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {

    private val _toDoList = MutableLiveData<List<ToDoModel>>()
    val toDoList: LiveData<List<ToDoModel>>
        get() = _toDoList

    fun getAllToDos() {
        _toDoList.value = ToDoManager.getAllToDos().reversed()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addToDo(title: String) {
        ToDoManager.addToDo(title)
        getAllToDos()
    }

    fun deleteToDo(id: Int) {
        ToDoManager.deleteToDo(id)
        getAllToDos()
    }
}