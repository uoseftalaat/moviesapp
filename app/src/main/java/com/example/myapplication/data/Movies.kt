package com.example.myapplication.data

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movies(
    var moviename: String,
    var moviedesc: String,
    var image: Bitmap,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)