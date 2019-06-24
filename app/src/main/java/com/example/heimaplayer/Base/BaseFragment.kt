package com.example.heimaplayer.Base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.toast

abstract class BaseFragment :Fragment() ,AnkoLogger{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * fragment初始化
     */
    protected open fun init() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView()
    }

    /**
     * 获取布局view
     */
    abstract fun initView(): View?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }
    /**
     * 数据的初始化
     */
    protected open fun initData() {
    }

    /**
     * adapter listener
     */
    protected open fun initListener() {
    }

    fun myToast(msg:String){
        //kotlin的?.和!!.  ?.表示当前对象如果为空则不执行，
        //!!.表示当前对象如果为空也执行，然后会抛出空异常
        context?.runOnUiThread {
            toast(msg)
        }
    }
}