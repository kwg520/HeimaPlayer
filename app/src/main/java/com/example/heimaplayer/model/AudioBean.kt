package com.example.heimaplayer.model

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore

@SuppressLint("ParcelCreator")
data class AudioBean(var data:String, var size:Long, var display_name:String,
                     var artist:String) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data)
        parcel.writeLong(size)
        parcel.writeString(display_name)
        parcel.writeString(artist)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AudioBean> {
        override fun createFromParcel(parcel: Parcel): AudioBean {
            return AudioBean(parcel)
        }

        override fun newArray(size: Int): Array<AudioBean?> {
            return arrayOfNulls(size)
        }

        fun getAudioBean(cursor: Cursor):AudioBean{

            //创建AudioBean 对象
            val audiobean = AudioBean("",0,"","")
            //判断cursor 是否为空
            cursor?.let {
                audiobean.data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                audiobean.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE))
                audiobean.display_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                audiobean.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
            }

            //解析cursor 对象并设置
            return  audiobean
        }

        //根据当前位置，获取音乐列表
        fun getBeans(cursor: Cursor?): ArrayList<AudioBean> {
            //创建当前集合
            val list = ArrayList<AudioBean>()

            //判断当前cursor是否为空
            cursor?.let {
                it.moveToPosition(-1)
                while (it.moveToNext()){
                    val audiobean = getAudioBean(cursor)
                    //解析cursor并添加当前集合
                    list.add(audiobean)
                }

            }
            return list
        }

    }

}