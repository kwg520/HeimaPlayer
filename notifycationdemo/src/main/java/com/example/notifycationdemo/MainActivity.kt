package com.example.notifycationdemo

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v?.id){
           R.id.showNotify->showNot()
            R.id.hideNotify->hideNot()
        }
    }


  companion object{
      val PUSH_NOTIFICATION_ID :Int =(0x001)
      val PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID"
      val PUSH_CHANNEL_NAME = "关于8.0的通知"

  }

    var manager :NotificationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showNotify.setOnClickListener(this)
        hideNotify.setOnClickListener(this)
        var args = intent.extras
         args?.let {
             Toast.makeText(this,"sdjf",Toast.LENGTH_SHORT).show()
         }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private fun showNot() {
        Toast.makeText(this,"dian----",Toast.LENGTH_SHORT).show()
        val importance =  NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(PUSH_CHANNEL_ID,PUSH_CHANNEL_NAME,importance)
        manager =  getSystemService(Context.NOTIFICATION_SERVICE)  as NotificationManager
        manager?.createNotificationChannel(channel)
        manager?.notify(PUSH_NOTIFICATION_ID,getNotification())


    }
    private fun hideNot() {
        manager?.cancel(PUSH_NOTIFICATION_ID)
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun getNotification(): Notification? {
        val bitmap = BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher)
        val notification  = NotificationCompat.Builder(this)
            .setChannelId(PUSH_CHANNEL_ID)
            .setTicker("正在播放歌曲：北京")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(bitmap)
            .setContentTitle("北京")
            .setContentText("汪峰")
            .setWhen(System.currentTimeMillis())
            .setContentIntent(getPendingIntent())
            .setOngoing(true)//不能滑动删除通知
             .build()
        return  notification

    }

    private fun getPendingIntent():PendingIntent? {
         val intent  = Intent(this,MainActivity::class.java)
        intent.putExtra("arg","从主体点击事件进入")
        return PendingIntent.getActivity(this,1
            ,intent,PendingIntent.FLAG_UPDATE_CURRENT)

    }


}
