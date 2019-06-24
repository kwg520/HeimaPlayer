package com.example.heimaplayer.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.heimaplayer.model.AudioBean
import com.example.heimaplayer.widget.PlayListItemView

class PopListAdapter(var list:List<AudioBean>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView :PlayListItemView? = null
        if(convertView==null){
           itemView = PlayListItemView(parent?.context)
        }else{
            itemView = convertView  as PlayListItemView
        }
        itemView.setData(list[position])
        return itemView
    }

    override fun getItem(position: Int): Any {
       return list.get(position)
    }

    override fun getItemId(position: Int): Long {
       return  position.toLong()
    }

    override fun getCount(): Int {
       return list.size
    }
}