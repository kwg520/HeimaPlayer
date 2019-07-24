package com.example.heimaplayer.ui.activity

import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import com.example.heimaplayer.Base.BaseActivity
import com.example.heimaplayer.Base.BaseFragment
import com.example.heimaplayer.R
import com.example.heimaplayer.ui.fragment.HomeFragment
import com.example.heimaplayer.ui.fragment.MvFragment
import com.example.heimaplayer.ui.fragment.VbangFragment
import com.example.heimaplayer.ui.fragment.YuedanFragment
import com.example.heimaplayer.util.FragmentUtil
import com.example.heimaplayer.util.ToolbarManger
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity() ,ToolbarManger {
    /**
     * 惰性加载
     */
    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    var homeFragment:BaseFragment?=null
    var mvFragment :BaseFragment ? = null
    var vbangFragment:BaseFragment?= null
    var yuedanFragment:BaseFragment?= null
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        initMainToobar()
    }

    override fun initListener() {

        bottomBar.setDefaultTab(R.id.tab_home)
        bottomBar.currentTabId
        Log.e("defaultid",bottomBar.currentTabId.toString())
        bottomBar.setOnTabSelectListener{
            val beginTransaction = supportFragmentManager.beginTransaction()
            hideFragment(beginTransaction)
            when(it){
                R.id.tab_home->
                    if(homeFragment==null){
                        homeFragment = FragmentUtil.fragmentUtil.getFragment(it)
                        beginTransaction.add(R.id.container, homeFragment!!,it.toString())
                    }else{
                        beginTransaction.show(homeFragment!!)
                    }
                R.id.tab_mv->
                    if(mvFragment==null){
                        mvFragment = FragmentUtil.fragmentUtil.getFragment(it)
                        beginTransaction.add(R.id.container, mvFragment!!,it.toString())
                    }else{
                        beginTransaction.show(mvFragment!!)
                    }
                R.id.tab_vbang->
                    if(vbangFragment==null){
                        vbangFragment =FragmentUtil.fragmentUtil.getFragment(it)
                        beginTransaction.add(R.id.container, vbangFragment!!,it.toString())
                    }else{
                        beginTransaction.show(vbangFragment!!)
                    }
                R.id.tab_yuedan->
                    if(yuedanFragment==null){
                        yuedanFragment = FragmentUtil.fragmentUtil.getFragment(it)
                        beginTransaction.add(R.id.container, yuedanFragment!!,it.toString())
                    }else{

                        beginTransaction.show(yuedanFragment!!)
                    }

            }

            beginTransaction.commit()

//            beginTransaction.add(R.id.container, FragmentUtil.fragmentUtil.getFragment(it)!!,it.toString())
//            beginTransaction.replace(R.id.container, FragmentUtil.fragmentUtil.getFragment(it)!!,it.toString())
//            beginTransaction.addToBackStack(null)
//            beginTransaction.commit()
        }
//          bottomBar.setOnNavigationItemSelectedListener {
//              val beginTransaction = supportFragmentManager.beginTransaction()
//              beginTransaction.replace(R.id.container, FragmentUtil.fragmentUtil.getFragment(it.itemId)!!,it.itemId.toString())
//              beginTransaction.commit()
//              true
//          }

    }

    private fun hideFragment(fragmentTransaction: FragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment!!)
        }

        if (mvFragment != null) {
            fragmentTransaction.hide(mvFragment!!)
        }
        if (vbangFragment != null) {
            fragmentTransaction.hide(vbangFragment!!)
        }

        if (yuedanFragment!= null) {
            fragmentTransaction.hide(yuedanFragment!!)
        }

    }


}
