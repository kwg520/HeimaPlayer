package com.example.heimaplayer.ui.fragment

import com.example.heimaplayer.Base.BaseListAdapter
import com.example.heimaplayer.Base.BaseListFragment
import com.example.heimaplayer.Base.BaseListPresent
import com.example.heimaplayer.adapter.MvListAdapter
import com.example.heimaplayer.model.MvPageBean
import com.example.heimaplayer.model.VideoPlayBean
import com.example.heimaplayer.present.impl.MvListPresentImpl
import com.example.heimaplayer.ui.activity.JiecaoVideoPlayerActivity
import com.example.heimaplayer.ui.activity.TextureVideoPlayerAcitivity
import com.example.heimaplayer.ui.activity.VideoPlayerAcitivity
import com.example.heimaplayer.ui.activity.llTextureVideoPlayerActivity
import com.example.heimaplayer.widget.MvitemView
import org.jetbrains.anko.support.v4.startActivity

class MvPageFragment : BaseListFragment<MvPageBean,MvPageBean.VideosBean,MvitemView>(){

    var code:String?  = "all"


    override fun init() {
        super.init()
        code= arguments?.getString("args")
    }

    override fun getSpecialPresent(): BaseListPresent {
        return MvListPresentImpl(code!!,this)
    }

    override fun getSpecialAdpter(): BaseListAdapter<MvPageBean.VideosBean, MvitemView> {
         return MvListAdapter()
     }

    override fun getList(respose: MvPageBean?): List<MvPageBean.VideosBean>? {
        return respose?.videos
    }

    override fun onDestroy() {
        super.onDestroy()
        present.onDestroy()
    }


    override fun initListener() {
        super.initListener()
        adapter.setMyListener {
            val videosBean = VideoPlayBean(it.videoId,it.title,it.subTitle)
//            myToast(it.toString())

//            http://www.yinyuetai.com/api/info/get-video-urls?videoId=3385669
//            startActivity<VideoPlayerAcitivity>(
//                "item" to videosBean
//            )
            startActivity<JiecaoVideoPlayerActivity>(
                "item" to videosBean
            )
        }
    }
}