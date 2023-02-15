package com.example.myapplication

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.ViewModel

class MoviesListFragment : Fragment() {
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        var movies =
            mutableListOf(
                Movies("spriderman:no way home","MCU movie filmed in 2021 9/10 rating",R.drawable.spiderman)
            ,Movies("avengers:endgame","MCU movie filmed in 2019 9/10 rating",R.drawable.avengers)
            ,Movies("blackadams","DCU movie filmed in 2022 no rating",R.drawable.images)
        )
        val adapter: movieadapter = movieadapter(movies)
        val movieview = view.findViewById<RecyclerView>(R.id.recycler)
        movieview.adapter = adapter
        movieview.layoutManager = LinearLayoutManager(view.context)
        return view
    }

    public fun movetomovie(pos:Int) {
        findNavController().navigate(R.id.action_moviesListFragment_to_movieFragment)
    }

}