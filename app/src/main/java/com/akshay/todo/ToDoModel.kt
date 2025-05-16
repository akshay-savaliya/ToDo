package com.akshay.todo

import java.util.Date

data class ToDoModel(
    val id: Int,
    val title: String,
    val createdAt: Date
)