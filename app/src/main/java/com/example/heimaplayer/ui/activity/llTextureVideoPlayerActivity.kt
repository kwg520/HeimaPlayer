package com.example.heimaplayer.ui.activity

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.view.Surface
import android.view.TextureView
import com.example.heimaplayer.Base.BaseActivity
import com.example.heimaplayer.R
import com.example.heimaplayer.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player_texture.*

class llTextureVideoPlayerActivity : BaseActivity(), TextureView.SurfaceTextureListener {
    override fun onSurfaceTextureSizeChanged(p0: SurfaceTexture?, p1: Int, p2: Int) {
        //view大小变化
    }

    override fun onSurfaceTextureUpdated(p0: SurfaceTexture?) {
        //视图更新
    }

    override fun onSurfaceTextureDestroyed(p0: SurfaceTexture?): Boolean {
        //关闭mediaplayer
        mediaPlayer?.let {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
        //视图销毁
        return true
    }

    override fun onSurfaceTextureAvailable(p0: SurfaceTexture?, p1: Int, p2: Int) {

            //视图可用

            mediaPlayer.setDataSource("http://he.yinyuetai.com/uploads/videos/common/9568016B49FCFF10362589530EA81713.mp4?sc\\u003dfaaf286230e40376\\u0026br\\u003d3205\\u0026vid\\u003d3385877\\u0026aid\\u003d3468\\u0026area\\u003dML\\u0026vst\\u003d0")
            mediaPlayer.setSurface(Surface(p0))//设置播放视频画面
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
                //旋转画面
                textureView.rotation = 100f
            }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_texture
    }

//    var videoPlayBean: VideoPlayBean? = null
    val mediaPlayer by lazy { MediaPlayer() }
    override fun initData() {
        //获取传递的数据
//        videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")

        textureView.surfaceTextureListener = this
    }
}