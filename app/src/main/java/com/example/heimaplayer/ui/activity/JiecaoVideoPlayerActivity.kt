package com.example.heimaplayer.ui.activity

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.example.heimaplayer.Base.BaseActivity
import com.example.heimaplayer.R
import com.example.heimaplayer.adapter.VideoPagerAdapter
import com.example.heimaplayer.model.ParseVideoBean
import com.example.heimaplayer.model.VideoPlayBean
import com.example.heimaplayer.net.ResposeHandler
import com.example.heimaplayer.net.VideoRequest
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
import kotlinx.android.synthetic.main.activity_video_player_jiecao.*

class JiecaoVideoPlayerActivity : BaseActivity(), ResposeHandler<ParseVideoBean> {
    override fun onError(type: Int, string: String?) {

    }

    override fun onSucess(type: Int, respose: ParseVideoBean) {
       var url  = respose.hdVideoUrl
        videoplayer.setUp(url,
            JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,title)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_jiecao
    }

    var title : String? = null
    override fun initData() {
        val data = intent.data
        println("data=$data")
        if (data == null) {
            //获取传递的数据
            val videosBean = intent.getParcelableExtra<VideoPlayBean>("item")
            title  = videosBean.title
            VideoRequest(0,
                "https://www.yinyuetai.com/api/info/get-video-urls?videoId="+videosBean.id,
                this).execute()

//            val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
            Log.e("========",videosBean.toString())
            //从应用内响应视频播放
//            videoplayer.setUp(videosBean.url,
//                JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videosBean.title)
        } else {
            if (data.toString().startsWith("http")) {
                //应用外网络视频请求
                //应用外响应
                videoplayer.setUp(data.toString(),
                    JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, data.toString())
            } else {
                //应用外的本地视频请求
                //应用外响应

                videoplayer.setUp(data.path,
                    JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, data.toString())
            }

        }


//        val jcVideoPlayerStandard = findViewById<View>(R.id.videoplayer) as JCVideoPlayerStandard
//        videoplayer.setUp(videoPlayBean.url,
//                JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoPlayBean.title)
//        jcVideoPlayerStandard.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640")
    }

    override fun onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        JCVideoPlayer.releaseAllVideos()
    }

    override fun initListener() {
        //适配viewpager
        viewPager.adapter = VideoPagerAdapter(supportFragmentManager)
        //radiogroup选中监听
        rg.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.rb1-> viewPager.currentItem = 0
                R.id.rb2-> viewPager.currentItem = 1
                R.id.rb3-> viewPager.currentItem = 2
            }
        }
        //viewpager选中状态监听
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            /**
             * 滑动状态改变的回调
             */
            override fun onPageScrollStateChanged(state: Int) {

            }

            /**
             * 滑动回调
             */
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            /**
             * 选中状态改变回调
             */
            override fun onPageSelected(position: Int) {
                when(position){
                    0->rg.check(R.id.rb1)
                    1->rg.check(R.id.rb2)
                    2->rg.check(R.id.rb3)
                }
            }

        })
    }



}