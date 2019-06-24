package com.example.heimaplayer.util

import com.example.heimaplayer.Base.BaseFragment
import com.example.heimaplayer.R
import com.example.heimaplayer.ui.fragment.HomeFragment
import com.example.heimaplayer.ui.fragment.MvFragment
import com.example.heimaplayer.ui.fragment.VbangFragment
import com.example.heimaplayer.ui.fragment.YuedanFragment

class FragmentUtil private constructor(){
    companion object{
        val fragmentUtil by lazy { FragmentUtil() }
    }
    private val homeFragment by lazy { HomeFragment() }
    private val mvFragment by lazy { MvFragment() }
    private val vbangFragment by lazy { VbangFragment() }
    private val yuedanFragment by lazy { YuedanFragment() }

    fun getFragment(tabId:Int):BaseFragment?{
        when(tabId){
            R.id.tab_home->return homeFragment
            R.id.tab_mv->return mvFragment
            R.id.tab_vbang->return vbangFragment
            R.id.tab_yuedan->return yuedanFragment
        }
        return null
    }

}