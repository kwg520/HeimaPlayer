package com.example.heimaplayer.Base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.heimaplayer.widget.LoadMoreView

abstract class BaseListAdapter<ITEMBEAN,ITEMVIEW:View> : RecyclerView.Adapter<BaseListAdapter.BaseListholder>() {

    private val list  = ArrayList<ITEMBEAN>()

    /**
     * 更新
     */

    fun updataList(list:List<ITEMBEAN>){
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    /**
     *加载更多
     */

    fun loadMoreList(list:List<ITEMBEAN>){
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListholder {

        return if(viewType==1) BaseListholder(LoadMoreView(parent.context))
                        else BaseListholder(getItView(parent.context))
    }

    abstract fun getItView(context: Context?): ITEMVIEW

    override fun getItemViewType(position: Int): Int {
        return if(position==list.size)  1 else 0
    }


    override fun getItemCount(): Int {
        return list.size+1
    }

    override fun onBindViewHolder(holder: BaseListholder, position: Int) {
        if(position==list.size) return
        val data  = list[position]
        val itemView = holder.itemView as ITEMVIEW
        RefreshItemView(itemView,data)

        itemView.setOnClickListener {
          listener?.let {
              it(data)
          }

        }

    }
//    var  listener:Listener<ITEMBEAN>? = null
//    interface Listener<ITEMBEAN>{
//        fun Onclick(data:ITEMBEAN)
//    }
//
//    fun setMyListener(listener:Listener<ITEMBEAN>){
//        this.listener = listener
//    }

    var listener:((itemben:ITEMBEAN)->Unit)? = null

    fun setMyListener(listener:((itemben:ITEMBEAN)->Unit)){
        this.listener = listener
    }

    abstract fun RefreshItemView(itemView: ITEMVIEW, data: ITEMBEAN)

    class BaseListholder(itemView: View) : RecyclerView.ViewHolder(itemView)

}