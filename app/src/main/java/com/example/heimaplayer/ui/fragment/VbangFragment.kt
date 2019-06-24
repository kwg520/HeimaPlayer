package com.example.heimaplayer.ui.fragment

import android.Manifest
import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.AsyncTask
import android.os.Build
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.heimaplayer.Base.BaseFragment
import com.example.heimaplayer.R
import com.example.heimaplayer.adapter.VbangAdapter
import com.example.heimaplayer.model.AudioBean
import com.example.heimaplayer.ui.activity.AudioPlayerActivity
import com.example.heimaplayer.util.CursorUtil
import kotlinx.android.synthetic.main.fragment_vbang.*
import org.jetbrains.anko.noButton
import org.jetbrains.anko.sdk27.coroutines.onItemClick
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.yesButton





class VbangFragment:BaseFragment() {// EasyPermissions.PermissionCallbacks

//      val handler = object : Handler(){
//          override fun handleMessage(msg: Message?) {
//              msg?.let {
//                  val cursor  = msg.obj as Cursor
//                 CursorUtil.logCursor(cursor)
//              }
//          }
//      }

    companion object{
         const val READ_EXTERNAL_STORAGE = 122
    }


    override fun init() {
        super.init()

    }

    override fun initView(): View? {
        return  View.inflate(context, R.layout.fragment_vbang,null)
    }

    override fun initData() {
        super.initData()
        handPermisson()
//        smsTask()


    }

    private fun handPermisson() {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        val checkselfPermisson = ActivityCompat.checkSelfPermission(context!!,permission)

        if(checkselfPermisson == PackageManager.PERMISSION_GRANTED){
            //已经获取
            loadata()
        }else{
            //没有权限
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity!!,permission)){
                //需要弹出
                alert ("我们只会访问你的音乐文件","温馨提示"){
                    yesButton { myRequestPermisson() }
                    noButton {  }
                }

            }else{
                //不需要弹出
                myRequestPermisson()
            }

        }
    }

    /**
     * 真正的权限权限申请
     */
    private fun myRequestPermisson() {
        val permission  = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        requestPermissions(permission,1)
    }


    //接受权限
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            loadata()
        }
    }


    private fun loadata() {
        val reslover = context?.contentResolver

//        Thread(Runnable {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                val cursor =   reslover?.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//                    arrayOf(MediaStore.Audio.Media.SIZE,MediaStore.Audio.Media.DATA,
//                        MediaStore.Audio.Media.DISPLAY_NAME,MediaStore.Audio.Media.ARTIST),null,null)
////                CursorUtil.logCursor(cursor)
//              val msg  =  Message.obtain()
//                msg.obj  =  cursor
//                handler.sendMessage(msg)
//            }
//        }).start()

//        AudioTask().execute(reslover)

        val handler :AsyncQueryHandler = object :AsyncQueryHandler(reslover){
            override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
                //查询完成回调到主线程当中
//               CursorUtil.logCursor(cursor)
                //刷新列表
//                 (cookie as VbangAdapter).notifyDataSetChanged()
                //设置数据源

                //刷新adapter

                (cookie as VbangAdapter).swapCursor(cursor)

            }
        }
        handler.startQuery(0,adapter,
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.SIZE,MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,MediaStore.Audio.Media.ARTIST),
            null,null,null)
    }

    var adapter:VbangAdapter?  = null
    override fun initListener() {
        adapter  = VbangAdapter(context,null)
        listView.adapter = adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            //跳转到音乐播放界面
            //获取数据集合
           val cursor  =  adapter?.getItem(position) as Cursor
            //通过当前位置获取整个列表
          val list:ArrayList<AudioBean> =   AudioBean.getBeans(cursor)

           startActivity<AudioPlayerActivity>("list" to list, "position" to position)
        }

    }

    class AudioTask : AsyncTask<ContentResolver, Void, Cursor>() {
        //后台执行任务，新线程
        override fun doInBackground(vararg params: ContentResolver?): Cursor ?{
            var cursor :Cursor? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                 cursor = params[0]?.query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    arrayOf(
                        MediaStore.Audio.Media.SIZE, MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.ARTIST
                    ), null, null
                )
//                CursorUtil.logCursor(cursor)
            }
            return   cursor
        }

        override fun onPostExecute(result: Cursor?) {
            super.onPostExecute(result)
            CursorUtil.logCursor(result)
        }
    }



//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
//    }

//    @AfterPermissionGranted(READ_EXTERNAL_STORAGE)
//    private fun smsTask() {
//        if (context?.let
//            {
//            EasyPermissions.hasPermissions(it, Manifest.permission.READ_EXTERNAL_STORAGE) }!!) {
//            // Have permission, do the thing!
////            Toast.makeText(activity, "TODO: SMS things", Toast.LENGTH_LONG).show()
//            loadata()
//        } else {
//            // Request one permission
//            EasyPermissions.requestPermissions(
//                this, getString(R.string.rationale_sms),
//                READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
//            )
//        }
//    }

//    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
//
//    }
//
//    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        adapter?.changeCursor(null)
    }
}