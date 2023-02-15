package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movies(
    val moviename: String,
    val moviedesc: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)