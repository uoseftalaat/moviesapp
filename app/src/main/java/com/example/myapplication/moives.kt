package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

data class Movies(val title:String, val description:String, val image:Int)

class movieadapter(var movieslist:List<Movies>):RecyclerView.Adapter<movieadapter.movieholder>(){
    inner class movieholder(view:View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movieitem,parent,false)
        return movieholder(view)
    }

    override fun onBindViewHolder(holder: movieholder, position: Int) {
        val movietitle = holder.itemView.findViewById<TextView>(R.id.movienametv)
        val moviedesc = holder.itemView.findViewById<TextView>(R.id.moviedescriptiontv)
        val movieimage = holder.itemView.findViewById<ImageView>(R.id.movieimage)
        movieimage.setImageResource(movieslist[position].image)
        movietitle.text = movieslist[position].title
        moviedesc.text = movieslist[position].description
        holder.itemView.setOnClickListener {
            val activity:AppCompatActivity = it.context as AppCompatActivity
            val fragment:MovieFragment = MovieFragment()
            val bundle:Bundle = Bundle()
            bundle.putString("title",movieslist[position].title)
            bundle.putString("description",movieslist[position].description)
            bundle.putInt("image",movieslist[position].image)
            fragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction().replace(R.id.source,fragment)
                .addToBackStack("movieslist")
                .commit()
            val trash:MoviesListFragment = MoviesListFragment()
        }

    }


    override fun getItemCount(): Int {
        return movieslist.size
    }
}
