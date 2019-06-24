package com.example.heimaplayer.adapter

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.heimaplayer.ui.fragment.MvPageFragment

class MvPageAdapter(  fm: FragmentManager?, val list: List<String>, val context: Context) :FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        val bundle  = Bundle()
        when {
            "全部"==list?.get(position) -> bundle.putString("args", "all")
            "内地"==list?.get(position) -> bundle.putString("args", "ml")
            "港台"==list?.get(position) -> bundle.putString("args", "ht")
            "欧美"==list?.get(position) -> bundle.putString("args", "us")
            "韩国"==list?.get(position) -> bundle.putString("args", "kr")
            "日本"==list?.get(position) -> bundle.putString("args", "jp")
        }
        return Fragment.instantiate(context,MvPageFragment::class.java.name,bundle)
    }

    override fun getCount(): Int {
      return list?.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list?.get(position)
    }


}