package com.example.heimaplayer.util

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.heimaplayer.R
import com.example.heimaplayer.ui.activity.SettingActivity

interface ToolbarManger {
    /**
     * 初始化主页面的toolbar
     *
     */
    val toolbar:Toolbar

    fun initMainToobar(){
        toolbar.title ="黑马影音"
        toolbar.inflateMenu(R.menu.main)
        toolbar.setOnMenuItemClickListener {
            toolbar.context.startActivity(Intent(toolbar.context,SettingActivity::class.java))
            true
        }
    }
    fun initSettingToolbar(){
        toolbar.title ="设置"
    }
}