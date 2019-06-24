package com.example.heimaplayer.ui.activity

import android.preference.PreferenceManager
import androidx.appcompat.widget.Toolbar
import com.example.heimaplayer.Base.BaseActivity
import com.example.heimaplayer.R
import com.example.heimaplayer.util.ToolbarManger
import org.jetbrains.anko.find

class SettingActivity :BaseActivity(),ToolbarManger {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutId(): Int {
        return  R.layout.activity_setting

    }

    override fun initData() {
        initSettingToolbar()
        val sp = PreferenceManager.getDefaultSharedPreferences(this)

        val push = sp.getBoolean("push", false)
        println("push==$push")
    }
}