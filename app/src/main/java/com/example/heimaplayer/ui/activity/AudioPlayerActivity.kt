package com.example.heimaplayer.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import com.example.heimaplayer.Base.BaseActivity
import com.example.heimaplayer.R
import com.example.heimaplayer.adapter.PopListAdapter
import com.example.heimaplayer.model.AudioBean
import com.example.heimaplayer.service.AudioService
import com.example.heimaplayer.service.Iservice
import com.example.heimaplayer.util.StringUtil
import com.example.heimaplayer.widget.PlayListPopWindow
import kotlinx.android.synthetic.main.activity_music_player_bottom.*
import kotlinx.android.synthetic.main.activity_music_player_middle.*
import kotlinx.android.synthetic.main.activity_music_player_top.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class AudioPlayerActivity :BaseActivity(), View.OnClickListener, SeekBar.OnSeekBarChangeListener,
    AdapterView.OnItemClickListener {
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //播放当前歌曲
        iService?.playCurrentPosition(position)
    }

    /**
     * 进度改变回调
     * progress:改变之后的进度
     * fromUser:true 通过用户手指拖动改变进度  false代表通过代码方式改变进度
     */
    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
       if(!p2) return
        iService?.seekto(p1)
        updataProgress(p1)


    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.state -> upDateState()
            R.id.mode-> upDateMode()
            R.id.pre->iService?.playPre()
            R.id.next->iService?.playNext()
            R.id.playlist -> showPopWindow()
        }
    }

    private fun showPopWindow() {
        window
        val list = iService?.getPlaylist()
        list?.let {
            val adapter  = PopListAdapter(list)
            val height = audio_player_bottom.height
            val popWindow  = PlayListPopWindow(this,adapter,this,window)
            popWindow.showAsDropDown(audio_player_bottom,0,height)
        }
    }

    private fun upDateMode() {
        iService?.setMode()
        updataModeButton()
    }

    private fun updataModeButton() {
       var mode1: Int? =  iService?.getMode()
        when(mode1){
            AudioService.MODE_ALL->mode.setImageResource(R.drawable.selector_btn_playmode_order)
            AudioService.MODE_SINGLE->mode.setImageResource(R.drawable.selector_btn_playmode_single)
            AudioService.MODE_RANDOM->mode.setImageResource(R.drawable.selector_btn_playmode_random)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private fun upDateState() {
        iService?.upDateState()
        upDateStateButton()
    }

    private fun upDateStateButton() {
        //获取当前播放状态
        val isPlaying = iService?.isPlay()
        isPlaying?.let {
            //根据状态更新图标
            if (isPlaying) {
                //播放
                state.setImageResource(R.drawable.selector_btn_audio_play)
                drawable?.start()
                hander.sendEmptyMessage(MESSGE)
            } else {
                //暂停
                state.setImageResource(R.drawable.selector_btn_audio_pause)
                drawable?.stop()
                hander.removeMessages(MESSGE)
            }
        }
    }

    override fun getLayoutId(): Int {
        return  R.layout.activity_audio_player
    }

    val concon by lazy {
        AudioConnection()
    }
    var drawable:AnimationDrawable?=null
    var duration:Int = 0
    var MESSGE :Int  = 0
    val hander  = object :Handler(){
        override fun handleMessage(msg: Message?) {
            when(msg?.what){
                MESSGE->startUpdataProgress()
            }
        }
    }

    override fun initListener() {
        super.initListener()
        state.setOnClickListener(this)
        back.setOnClickListener { finish() }
        progress_sk.setOnSeekBarChangeListener(this)
        mode.setOnClickListener(this)
        pre.setOnClickListener(this)
        next.setOnClickListener(this)
        playlist.setOnClickListener(this)

    }

    var itemBean:AudioBean?=null

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(itemBean:AudioBean) {
        this.itemBean = itemBean
        audio_title.text  = itemBean.display_name
        artist.text = itemBean.artist
        upDateStateButton()
        //动画播放
        drawable =  this?.audio_anim.drawable as AnimationDrawable
        drawable?.start()



        //更新播放进度
        duration =  iService?.getDuration()?:0
        //
        progress_sk.max = duration

        startUpdataProgress()

    }

    /**
     * 开始更新进度
     */
    private fun startUpdataProgress() {

        //获取当前进度
       var progress =  iService?.getProgress()?:0
        //更新进度数据
        updataProgress(progress)

        //定时更新进度
        hander.sendEmptyMessageDelayed(MESSGE,1000)

    }

    /**
     * 更新界面
     */
    private fun updataProgress(pro: Int) {

      progress.text  = StringUtil.parseDuration(pro)+"/"+StringUtil.parseDuration(duration)
      progress_sk.progress = pro
    }


    override fun initData() {
        EventBus.getDefault().register(this)
        val intent  = intent
       intent.setClass(this,AudioService::class.java)

        //先开启
        bindService(intent,concon, Context.BIND_AUTO_CREATE)
        startService(intent)


    }


    override fun onDestroy() {
        super.onDestroy()
        unbindService(concon)
        EventBus.getDefault().unregister(this)
        //消息队列的
        hander.removeCallbacksAndMessages(null)

    }
    var iService : Iservice? = null
   inner class AudioConnection :ServiceConnection{


       /**
        *service 连接时
        */

       override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
         iService = service as Iservice

       }
        /**
         * 异常断开链接
         */
        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }





}