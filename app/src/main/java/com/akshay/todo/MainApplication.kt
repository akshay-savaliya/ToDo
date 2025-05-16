package com.akshay.todo

import android.app.Application
import androidx.room.Room
import com.akshay.todo.db.ToDoDatabase

class MainApplication : Application() {

    companion object {
        lateinit var toDoDatabase: ToDoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        toDoDatabase = Room.databaseBuilder(
            applicationContext,
            ToDoDatabase::class.java,
            ToDoDatabase.DATABASE_NAME
        ).build()
    }
}