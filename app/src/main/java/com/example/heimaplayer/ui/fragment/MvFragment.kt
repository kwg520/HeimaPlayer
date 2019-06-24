package com.example.heimaplayer.ui.fragment

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.heimaplayer.Base.BaseFragment
import com.example.heimaplayer.Base.BaseListView
import com.example.heimaplayer.R
import com.example.heimaplayer.adapter.MvPageAdapter
import com.example.heimaplayer.model.MvPageBean
import com.example.heimaplayer.present.impl.MVPresentImpl
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.support.v4.find

class MvFragment:BaseFragment(), BaseListView<MvPageBean> {

    /**
     * mainView
     */
    private var mainView: View? = null
    override fun OnFailure(message: String?) {
    }

    override fun OnSucess(respose: MvPageBean) {
    }

    override fun loadMore(respose: MvPageBean) {
    }

    private val tabcontent by lazy{
        find<TabLayout>(R.id.tb_content)
    }
    private val vpcontent by lazy{
        find<ViewPager>(R.id.vp_content)
    }
    private val adapter by lazy{
        context?.let { MvPageAdapter(childFragmentManager,list, it) }
    }

//    val fragmentlist : ArrayList<Fragment>? = null
    val present by lazy { MVPresentImpl(this) }
    val list  = listOf("全部","内地","港台","欧美","韩国","日本")
    override fun initView(): View? {

        mainView ?: let {
            mainView = View.inflate(context, R.layout.fragment_my, null)


        }
        return mainView
    }

    override fun initListener() {




    }

    override fun initData() {

        for(title in list){
            tabcontent.addTab(tabcontent.newTab().setText(title))
        }

        vpcontent.adapter =adapter
        vpcontent.offscreenPageLimit = list.size
        vpcontent.currentItem = 0
        tabcontent.setupWithViewPager(vpcontent)
//        present.loadData()

    }
}