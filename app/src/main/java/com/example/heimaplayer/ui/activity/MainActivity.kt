package com.example.heimaplayer.ui.activity

import androidx.appcompat.widget.Toolbar
import com.example.heimaplayer.Base.BaseActivity
import com.example.heimaplayer.R
import com.example.heimaplayer.util.FragmentUtil
import com.example.heimaplayer.util.ToolbarManger
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity() ,ToolbarManger {
    /**
     * 惰性加载
     */
    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        initMainToobar()
    }

    override fun initListener() {

        bottomBar.setOnTabSelectListener{
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.container, FragmentUtil.fragmentUtil.getFragment(it)!!,it.toString())
            beginTransaction.commit()
        }
    }

}
