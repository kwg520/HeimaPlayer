package com.example.heimaplayer.service

import android.annotation.TargetApi
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.heimaplayer.R
import com.example.heimaplayer.model.AudioBean
import com.example.heimaplayer.ui.activity.AudioPlayerActivity
import com.example.heimaplayer.ui.activity.MainActivity
import org.greenrobot.eventbus.EventBus
import kotlin.random.Random


class AudioService : Service() {


    val PUSH_NOTIFICATION_ID :Int =(0x001)
    val PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID"
    val PUSH_CHANNEL_NAME = "关于8.0的通知"

    var manager:NotificationManager? = null
    var notification: Notification? = null
    val FROM_PRE= 1
    val FROM_NEXT= 2
    val FROM_STATE= 3
    val FROM_CONTENT= 4

    companion object {
        val MODE_ALL = 1
        val MODE_SINGLE = 2
        val MODE_RANDOM = 3
    }

    val sp by lazy {
        getSharedPreferences("config", Context.MODE_PRIVATE)
    }
    var list: ArrayList<AudioBean>? = null
    var position: Int = -2
    var mediaPlayer: MediaPlayer? = null
    val audioBinder by lazy {
        AudioBinder()
    }

    var mode = MODE_ALL
    override fun onCreate() {
        super.onCreate()
        mode = sp.getInt("mode", 1)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

      val from =  intent?.getIntExtra("from",-1)
      when(from){
          FROM_PRE->{audioBinder.playPre()}
          FROM_NEXT->{audioBinder.playNext()}
          FROM_CONTENT->{audioBinder.notifyUpdataUi()}
          FROM_STATE->{audioBinder.updatePlayState()}
          else->{
              val pos = intent?.getIntExtra("position", -1) ?: -1 // 想要播放的position
              if(pos!=position){//想要播放的条目和正在播放条目不是同一首
                  position = pos
                  println("intent=$intent")
                  //获取集合以及position
                  list = intent?.getParcelableArrayListExtra("list")
                  //开始播放音乐
                  audioBinder.playItem()
              }else{
                  //主动通知界面更新
                  audioBinder.notifyUpdataUi()
              }
          }
      }
//       val pos = intent?.getIntExtra("position", -1)?:-1
//        if(pos!=position){
//            position = pos
//            list = intent?.getParcelableArrayListExtra<AudioBean>("list")
//            audioBinder.playItem()
//        }else{
//           audioBinder.notifyUpdataUi()
//        }


//        START_STICKY  粘性的,会重启service，杀死之后不会再传递 intent

//        START_NOT_STICKY 不会尝试重启service

//        START_REDELIVER_INTENT 重启service,会传递之前的intent

        return START_REDELIVER_INTENT

    }

    override fun onBind(intent: Intent?): IBinder? {
        return audioBinder
    }


    inner class AudioBinder : Binder(), Iservice, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
        override fun playCurrentPosition(position: Int) {
            this@AudioService.position =position
            playItem()
        }

        //获取当前播放集合
        override fun getPlaylist(): List<AudioBean>? {
            return list
        }

        override fun playPre() {
            list?.let {
                position = when (mode) {
                    MODE_RANDOM -> Random.nextInt(it.size - 1)
                    else -> {
                        if (position == 0) {
                            it.size - 1
                        } else {
                            position--
                        }
                    }


                }
            }
            playItem()
        }

        override fun playNext() {
            list?.let {
                position = when (mode) {
                    MODE_RANDOM -> Random.nextInt(it.size - 1)
                    else -> {
                        (position + 1) % it.size
                    }
                }
            }
            playItem()
        }

        override fun getMode(): Int {
            return mode
        }

        override fun setMode() {
            when (mode) {
                MODE_ALL -> mode = MODE_SINGLE
                MODE_SINGLE -> mode = MODE_RANDOM
                MODE_RANDOM -> mode = MODE_ALL

            }
            sp.edit().putInt("mode", mode).commit()

        }

        override fun onCompletion(mp: MediaPlayer?) {
            autoPlayNext()
            playItem()
        }

        override fun seekto(progress: Int) {
            mediaPlayer?.seekTo(progress)
        }

        override fun getProgress(): Int {
            return mediaPlayer?.currentPosition ?: 0
        }

        override fun getDuration(): Int {
            return mediaPlayer?.duration ?: 0
        }

        override fun isPlay(): Boolean? {
            return mediaPlayer?.isPlaying
        }

        override fun upDateState() {
            isPlay().let {
                if (it == true) {
                   pause()
                } else {
                   start()
                }
            }

        }

        override fun onPrepared(mp: MediaPlayer?) {
            start()
            notifyUpdataUi()
            //显示通知
            showNotification()

        }

        fun playItem() {
            if (mediaPlayer != null) {
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }

            mediaPlayer = MediaPlayer()
            mediaPlayer?.let {
                it.setOnPreparedListener(this)
                it.setDataSource(list?.get(position)?.data)
                it.prepareAsync()
                it.setOnCompletionListener(this)
            }

        }

        fun notifyUpdataUi() {
            EventBus.getDefault().post(list?.get(position))
        }


        /**
         * 更新播放状态
         */
        fun updatePlayState() {
            //获取当前播放状态
            val isPlaying = isPlaying()
            //切换播放状态
            isPlaying?.let {
                if (isPlaying) {
                    //播放 暂停
                    pause()
                } else {
                    //暂停 播放
                    start()
                }
            }
        }
    }


    /**
     * 暂停
     */
    private fun pause() {
        mediaPlayer?.pause()
        EventBus.getDefault().post(list?.get(position))
        //更新图标

        notification?.contentView?.setImageViewResource(R.id.state,R.mipmap.btn_audio_pause_normal)
        //重新显示
//        manager?.notify(1,notification)
        manager?.notify(PUSH_NOTIFICATION_ID,notification)
    }

    /**
     * 开始
     */
    private fun start(){
        mediaPlayer?.start()
        EventBus.getDefault().post(list?.get(position))
        //更新图标
        notification?.contentView?.setImageViewResource(R.id.state,R.mipmap.btn_audio_play_normal)
        //重新显示

//        val importance =  NotificationManager.IMPORTANCE_HIGH
//        val channel = NotificationChannel(PUSH_CHANNEL_ID,PUSH_CHANNEL_NAME,importance)
//        manager?.createNotificationChannel(channel)
        manager?.notify(PUSH_NOTIFICATION_ID,notification)
    }

    private fun isPlaying(): Boolean? {
        return mediaPlayer?.isPlaying
    }

    /**
     * 显示通知
     */
    @TargetApi(Build.VERSION_CODES.O)
    private fun showNotification() {
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notification = getNotifications()
//        manager?.notify(1,notification)
        val importance =  NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(PUSH_CHANNEL_ID,PUSH_CHANNEL_NAME,importance)
        manager?.createNotificationChannel(channel)
        manager?.notify(PUSH_NOTIFICATION_ID,notification)
    }

    private fun getNotifications(): Notification? {
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        val notification = NotificationCompat.Builder(this@AudioService)
            .setTicker("正在播放歌曲${list?.get(position)?.display_name}")
            .setChannelId(PUSH_CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(bitmap)
//                .setContentTitle("北京")//通知标题
//                .setContentText("汪峰")//通知内容
            //自定义通知view
            .setCustomContentView(getRemoteViews())
            .setWhen(System.currentTimeMillis())
            .setOngoing(true)//设置不能滑动删除通知
            .setContentIntent(getPendingIntent())//通知栏主体点击事件
            .build()
        return notification
    }

    private fun getRemoteViews(): RemoteViews? {
        val remoteViews = RemoteViews(packageName,R.layout.notification)
        //修改标题和内容
        remoteViews.setTextViewText(R.id.title,list?.get(position)?.display_name)
        remoteViews.setTextViewText(R.id.artist,list?.get(position)?.artist)
        //处理上一曲 下一曲  状态点击事件
        remoteViews.setOnClickPendingIntent(R.id.pre,getPrePendingIntent())
        remoteViews.setOnClickPendingIntent(R.id.state,getStatePendingIntent())
        remoteViews.setOnClickPendingIntent(R.id.next,getNextPendingIntent())
        return remoteViews
    }

    /**
     * 下一曲点击事件
     */
    private fun getNextPendingIntent(): PendingIntent? {
        val intent = Intent(this@AudioService,AudioService::class.java)//点击主体进入当前界面中
        intent.putExtra("from",FROM_NEXT)
        val pendingIntent = PendingIntent.getService(this@AudioService,2,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent
    }

    /**
     * 播放暂停按钮点击事件
     */
    private fun getStatePendingIntent(): PendingIntent? {
        val intent = Intent(this@AudioService,AudioService::class.java)//点击主体进入当前界面中
        intent.putExtra("from",FROM_STATE)
        val pendingIntent = PendingIntent.getService(this@AudioService,3,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent
    }

    /**
     * 上一曲点击事件
     */
    private fun getPrePendingIntent(): PendingIntent? {
        val intent = Intent(this@AudioService, AudioService::class.java)//点击主体进入当前界面中
        intent.putExtra("from",FROM_PRE)
        val pendingIntent = PendingIntent.getService(this@AudioService,4,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent
    }

    /**
     * 通知栏主体点击事件
     */
    private fun getPendingIntent(): PendingIntent? {
        val intentM = Intent(this@AudioService, MainActivity::class.java)//点击主体进入当前界面中
        val intentA = Intent(this@AudioService, AudioPlayerActivity::class.java)//点击主体进入当前界面中
        intentA.putExtra("from",FROM_CONTENT)
        val intents = arrayOf(intentM,intentA)
        val pendingIntent = PendingIntent.getActivities(this@AudioService,1,intents, PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent
    }


    private fun autoPlayNext() {
        when (mode) {
            MODE_ALL -> {
                list?.let {
                    position = (position + 1) % it.size
                }
            }
            MODE_RANDOM -> {
                list?.let {
                    position = Random.nextInt(it.size)
                }
            }
//            MODE_SINGLE->{
//
//            }
        }


    }


}