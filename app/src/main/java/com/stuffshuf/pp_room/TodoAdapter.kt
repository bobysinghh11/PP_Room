package com.stuffshuf.pp_room

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.list_items.view.*

class TodoAdapter(var tasks: ArrayList<Todo>) : BaseAdapter() {



    var listItemClickList:ListItemClickList?=null

    fun updateTasks(newTasks: ArrayList<Todo>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val li = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view = li.inflate(android.R.layout.simple_list_item_1, parent, false)
//
//
//        view.findViewById<TextView>(android.R.id.text1).text=getItem(position).task
        val view=li.inflate(R.layout.list_items, parent, false)
        view.tvresult.text=getItem(position).task

        view.checkbox.setOnClickListener {
            listItemClickList?.checkBoxListener(getItem(position), position)

        }
        view.findViewById<ImageButton>(R.id.imgDel).setOnClickListener {
            listItemClickList?.delButtonListener(getItem(position), position)

        }



        if (getItem(position).status) {
            view.findViewById<TextView>(R.id.tvresult).setTextColor(Color.RED)
            view.findViewById<CheckBox>(R.id.checkbox).isChecked=true

        }



        return view
    }

    override fun getItem(position: Int): Todo = tasks[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = tasks.size

}