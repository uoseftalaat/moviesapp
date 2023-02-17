package com.example.myapplication.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {
    @TypeConverter
    fun fromBitmap(image:Bitmap):ByteArray{
        val output = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG,100,output)
        return output.toByteArray()
    }
    @TypeConverter
    fun toBitmap(image:ByteArray):Bitmap{
        return BitmapFactory.decodeByteArray(image,0,image.size)
    }
}