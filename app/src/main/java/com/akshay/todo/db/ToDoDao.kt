package com.akshay.todo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akshay.todo.ToDoModel

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo ORDER BY createdAt DESC")
    fun getAllToDos(): LiveData<List<ToDoModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToDo(todo: ToDoModel)

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun deleteToDo(id: Int)
}