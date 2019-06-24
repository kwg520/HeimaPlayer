package com.example.heimaplayer.ui.activity

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.util.Log
import android.view.Surface
import android.view.TextureView
import com.example.heimaplayer.Base.BaseActivity
import com.example.heimaplayer.R
import com.example.heimaplayer.model.ParseVideoBean
import com.example.heimaplayer.model.VideoPlayBean
import com.example.heimaplayer.net.ResposeHandler
import com.example.heimaplayer.net.VideoRequest
import kotlinx.android.synthetic.main.activity_videoplayerrtext.*

class TextureVideoPlayerAcitivity  :BaseActivity(), ResposeHandler<ParseVideoBean>, TextureView.SurfaceTextureListener {
    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {

    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
        //view的更新
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
        mediaPlayer?.let {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
       return true
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
           this.surface = surface
    }

    override fun onError(type: Int, string: String?) {
        myToast(string+"")
    }

    var surface: SurfaceTexture? =null
    var url :String = ""

    private val mediaPlayer by lazy { MediaPlayer() }

    override fun onSucess(type: Int, respose: ParseVideoBean) {

//        video_player.setVideoPath(respose.hcVideoUrl)
//        //设置准备播放，视频较大时准备
//        video_player.setOnPreparedListener {
//            video_player.start()
//        }

        url = respose.hdVideoUrl
        myToast(url)

        mediaPlayer?.let {
            mediaPlayer.setDataSource(url)
            myToast(url)
            mediaPlayer.setSurface(Surface(surface))//设置播放视频画面
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
//                textureView.rotation= 100f
            }
        }


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_videoplayerrtext
    }

    override fun initData() {
        super.initData()


        val videosBean = intent.getParcelableExtra<VideoPlayBean>("item")
        VideoRequest(0,
            "http://www.yinyuetai.com/api/info/get-video-urls?videoId="+videosBean.id,
            this).execute()
        Log.e("kwg---",videosBean.toString())

        textureView.surfaceTextureListener   = this


    }
}