package com.example.heimaplayer.Base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 所有activity的基类
 */
 abstract class BaseActivity:AppCompatActivity(),AnkoLogger {
//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onCreate(savedInstanceState, persistentState)
//        setContentView(getLayoutId())
//        initListener()
//        initData()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListener()
        initData()
    }
    /**
     * 获取数据，获取数据和初始化数据的地方
      */
    protected open fun initData(){
    }


    /**
     * 获取adapter 和view 的一些监听
     */
    protected open fun initListener(){
    }
    /**
     * 获取布局id
     */
    abstract fun getLayoutId(): Int

    /**
     * 防止在非Ui线程弹吐司
     */

    protected open fun myToast(content:String){
       runOnUiThread {
           toast(content)
       }
    }

    /**
     * 跳转并结束当前activity
     */
    inline fun <reified T : BaseActivity> startAndFinishActivity(){
        startActivity<T>()
        finish()
    }


}