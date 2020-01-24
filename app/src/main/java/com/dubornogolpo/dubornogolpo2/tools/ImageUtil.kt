package com.dubornogolpo.dubornogolpo2.tools

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

class ImageUtil()
{
    fun bmp2byte(bmp: Bitmap): ByteArray {
        var stream: ByteArrayOutputStream? = null
        bmp.compress(Bitmap.CompressFormat.PNG, 0, stream)
        stream!!.close()
        return stream!!.toByteArray()
    }

    fun byte2bmp(bytes: ByteArray): Bitmap
    {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
}