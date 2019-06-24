package com.example.heimaplayer.util

import android.database.Cursor

object CursorUtil {

    fun logCursor(cursor: Cursor?) {

        cursor?.let {
            it.moveToPosition(-1)
            while (it.moveToNext()) {
                for (index in 0 until it.columnCount) {
                    println("key=${it.getColumnName(index)} --value=${it.getString(index)}")
                }
            }
        }
    }
}