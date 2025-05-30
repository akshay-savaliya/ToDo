package com.akshay.todo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.akshay.todo.model.ToDoModel

@Database(entities = [ToDoModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class ToDoDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "todo_database"
    }

    abstract fun getToDoDao(): ToDoDao

}