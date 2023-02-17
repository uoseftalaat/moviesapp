package com.example.myapplication

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class MovieFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie, container, false)
        val args = this.arguments
        val title = view.findViewById<TextView>(R.id.movienametv)
        val desc = view.findViewById<TextView>(R.id.moviedescriptiontv)
        val image = view.findViewById<ImageView>(R.id.movieimage)
        title.text = args?.getString("title")
        desc.text = args?.getString("description")
        val imag = args?.getByteArray("image")
        if(imag != null)

            image.setImageBitmap(BitmapFactory.decodeByteArray(imag,0,imag.size))
        return view
    }

}