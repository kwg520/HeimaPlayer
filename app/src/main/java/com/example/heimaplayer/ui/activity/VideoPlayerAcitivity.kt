package com.example.heimaplayer.ui.activity

import android.util.Log
import com.example.heimaplayer.Base.BaseActivity
import com.example.heimaplayer.R
import com.example.heimaplayer.model.ParseVideoBean
import com.example.heimaplayer.model.VideoPlayBean
import com.example.heimaplayer.net.ResposeHandler
import com.example.heimaplayer.net.VideoRequest
import kotlinx.android.synthetic.main.activity_videoplayerr.*

class VideoPlayerAcitivity  :BaseActivity(), ResposeHandler<ParseVideoBean> {
    override fun onError(type: Int, string: String?) {
        myToast(string+"")
    }

    override fun onSucess(type: Int, respose: ParseVideoBean) {

        video_player.setVideoPath(respose.hcVideoUrl)
        //设置准备播放，视频较大时准备
        video_player.setOnPreparedListener {
            video_player.start()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_videoplayerr
    }

    override fun initData() {
        super.initData()
        val videosBean = intent.getParcelableExtra<VideoPlayBean>("item")
        VideoRequest(0,
            "https://www.yinyuetai.com/api/info/get-video-urls?videoId="+videosBean.id,
            this).execute()
        Log.e("kwg---",videosBean.toString())



    }
}