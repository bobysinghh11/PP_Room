package com.stuffshuf.pp_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id:Long?= null,
    val task:String,
   // @ColumnInfo(name = "done-is")
    var status:Boolean
   // val phoneNumber:Int
    )
