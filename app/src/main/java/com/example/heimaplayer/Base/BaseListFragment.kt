package com.example.heimaplayer.Base

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heimaplayer.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.toast

/**
 * view  完成
 *
 * present 完成
 *
 * adapter
 *
 */

abstract class BaseListFragment<RESPOSE,ITEMBEAN,ITEMVIEW:View> : BaseFragment(), BaseListView<RESPOSE> {
    val adapter by lazy { getSpecialAdpter() }

    val present by lazy { getSpecialPresent() }



    override fun OnFailure(message: String?) {
        toast("加载失败")
    }

    override fun OnSucess(respose: RESPOSE) {
        toast("刷新成功")
        refresh.isRefreshing = false
        getList(respose)?.let { adapter.updataList(it) }
    }



    override fun loadMore(respose: RESPOSE) {
        refresh.isRefreshing = false
        toast("获取数据成功")
        getList(respose)?.let { adapter.loadMoreList(it) }
    }


    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home, null)
    }

    override fun initListener() {
        recycleView.layoutManager = LinearLayoutManager(context)

        recycleView.adapter = adapter
        refresh.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        //刷新
        refresh.setOnRefreshListener {
            present.loadData()
        }
        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        val layoutManager: LinearLayoutManager = layoutManager
                        if (layoutManager.findLastVisibleItemPosition() == adapter.itemCount - 1) {
                            present.loadMore(adapter.itemCount -10)
                        }

                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            }
        })

    }

    override fun initData() {
        present.loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        present.onDestroy()
    }

    abstract fun getSpecialPresent(): BaseListPresent
    abstract fun getSpecialAdpter(): BaseListAdapter<ITEMBEAN,ITEMVIEW>
    abstract fun getList(respose: RESPOSE?): List<ITEMBEAN>?
}