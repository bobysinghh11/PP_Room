package com.stuffshuf.pp_room

import java.text.FieldPosition

interface ListItemClickList {

    fun checkBoxListener(todo: Todo, position: Int)
    fun delButtonListener(todo: Todo, position: Int)
}