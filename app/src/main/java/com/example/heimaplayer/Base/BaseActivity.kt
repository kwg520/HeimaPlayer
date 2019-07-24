package com.example.heimaplayer.Base

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import kotlin.system.exitProcess

/**
 * 所有activity的基类
 */
 abstract class BaseActivity:AppCompatActivity(),AnkoLogger {

    var mExitTime : Long = 0
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


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                myToast("再按一次退出程序")
                mExitTime = System.currentTimeMillis()
            } else {
                finish()
                exitProcess(0)
                System.gc()
            }
            return true
        }
        // 拦截MENU按钮点击事件，让他无任何操作
        return if (keyCode == KeyEvent.KEYCODE_MENU) {
            true
        } else super.onKeyDown(keyCode, event)
    }

}