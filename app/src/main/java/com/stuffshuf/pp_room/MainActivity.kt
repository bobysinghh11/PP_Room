package com.stuffshuf.pp_room

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Toolbar
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_items.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    var list= arrayListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db=Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "todo.db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        list=db.todoDao().getAllTodo()  as ArrayList<Todo>

          val todoAdapter=TodoAdapter(list)
        lvTodolist.adapter=todoAdapter




        btnAdd.setOnClickListener {

            db.todoDao().insertRow(
                Todo(
                 task= etNewItem.text.toString(),
                    status = false

            )
            )
            list=db.todoDao().getAllTodo() as ArrayList<Todo>
            todoAdapter.updateTasks(list)
        }



        searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
//
//                var task = "%$query%"
//                db.todoDao().SearchTodo(task)
//                list=db.todoDao().getAllTodo() as ArrayList<Todo>
//                todoAdapter.updateTasks(list)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                  var tasks = newText!!
                  db.todoDao().SearchTodo(tasks)
                  list=db.todoDao().getAllTodo() as ArrayList<Todo>
                todoAdapter.updateTasks(list)
                return true

                }


        })

btnDel.setOnClickListener {

   db.todoDao().deleteDone()
    list=db.todoDao().getAllTodo() as ArrayList<Todo>

    todoAdapter.updateTasks(list)

}

        deleteAll.setOnClickListener {

            db.todoDao().deleteAll()
            list=db.todoDao().getAllTodo() as ArrayList<Todo>
            todoAdapter.updateTasks(list)

        }

        showData.setOnClickListener {

            list= db.todoDao().getAllTodo() as ArrayList<Todo>
            todoAdapter.updateTasks(list)
        }



        searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
            return false
              }

            override fun onQueryTextChange(newText: String?): Boolean {
                val tasks=""+newText+"%"  // --> ant to search by starting char of the name
                //val task= newtext  --> want to search by complete name
               // val tasks="%"+newText+"" --> want to search by last char of the name

               list = db.todoDao().SearchTodo(tasks) as ArrayList<Todo>
                todoAdapter.updateTasks(list)

                return true

            }

        })

        lvTodolist.onItemClickListener=object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val newTask=todoAdapter.getItem(position)
                newTask.status = ! newTask.status
                checkbox.isChecked=!checkbox.isChecked
                db.todoDao().updateTaska(newTask)
                list=db.todoDao().getAllTodo() as ArrayList<Todo>
                todoAdapter.updateTasks(list)

            }

        }

        deleteDone.setOnClickListener {
          list =   db.todoDao().getAllTodoWithFalse(false) as ArrayList<Todo>
           // list = db.todoDao().getAllTodo() as ArrayList<Todo>
            todoAdapter.updateTasks(list)

            Log.d("delete", "de $list")


        }


        btnsort.setOnClickListener {

             list =  db.todoDao().sortTask() as ArrayList<Todo>
              todoAdapter.updateTasks(list)

        }

        todoAdapter.listItemClickList=object :ListItemClickList{
            override fun delButtonListener(todo: Todo, position: Int) {
               db.todoDao().delete(todo)
                list=db.todoDao().getAllTodo() as ArrayList<Todo>
                todoAdapter.updateTasks(list)
                Log.d("Hello", "td $todo")




1

            }

            override fun checkBoxListener(todo: Todo, position: Int) {
                todo.status=!todo.status
                db.todoDao().updateTaska(todo)
                list=db.todoDao().getAllTodo() as ArrayList<Todo>
                todoAdapter.updateTasks(list)
             //   tvresult.setTextColor(Color.RED)
            }

        }

    }
}
