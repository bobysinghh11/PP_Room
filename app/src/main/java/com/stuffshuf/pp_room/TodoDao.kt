package com.stuffshuf.pp_room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Insert
    fun insertRow(todo: Todo)

    @Insert
    fun inserAll(todoList: ArrayList<Todo>)


    @Query("Select * FROM Todo")
    fun getAllTodo(): List<Todo>

    @Query("Select * FROM Todo WHERE status = :task")
    fun getAllTodoWithFalse(task:Boolean):List<Todo>

    @Delete
    fun delete(todo: Todo)

    @Query("Delete FROM Todo WHERE status=1")
    fun deleteDone()

    @Query("Delete From Todo")
    fun deleteAll()

    @Update
    fun updateTaska(todo: Todo)

    @Query("Select * FROM Todo ORDER BY status ASC")
    fun sortTask():List<Todo>

    @Query("Select * FROM Todo WHERE task LIKE :task")
    fun SearchTodo(task:String):List<Todo>

}

