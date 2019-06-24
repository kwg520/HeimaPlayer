package com.example.heimaplayer.adapter

import android.content.Context
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import com.example.heimaplayer.model.AudioBean
import com.example.heimaplayer.widget.VbangItemView

class VbangAdapter(context: Context?, c: Cursor?) : CursorAdapter(context, c) {
    /**
     * 创建条目view
     */
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return VbangItemView(context)
    }

    /**
     * view +date
     */
    override fun bindView(view: View?, context: Context?, cursor: Cursor) {
        val itemview  =  view as VbangItemView
        val itemBean = AudioBean.getAudioBean(cursor)
        itemview.setData(itemBean)
    }
}