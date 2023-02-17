package com.example.myapplication

import android.graphics.Bitmap
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
import com.example.myapplication.data.Converters
import com.example.myapplication.data.Movies
import java.io.ByteArrayOutputStream


class movieadapter():RecyclerView.Adapter<movieadapter.movieholder>(){
    inner class movieholder(view:View):RecyclerView.ViewHolder(view){

    }
    var movieslist = emptyList<Movies>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movieitem,parent,false)
        return movieholder(view)
    }

    override fun onBindViewHolder(holder: movieholder, position: Int) {
        val movietitle = holder.itemView.findViewById<TextView>(R.id.movienametv)
        val moviedesc = holder.itemView.findViewById<TextView>(R.id.moviedescriptiontv)
        val movieimage = holder.itemView.findViewById<ImageView>(R.id.movieimage)
        movieimage.setImageBitmap(movieslist[position].image)
        movietitle.text = movieslist[position].moviename
        moviedesc.text = movieslist[position].moviedesc
        holder.itemView.setOnClickListener {
            val activity:AppCompatActivity = it.context as AppCompatActivity
            val fragment:MovieFragment = MovieFragment()
            val bundle:Bundle = Bundle()
            bundle.putString("title",movieslist[position].moviename)
            bundle.putString("description",movieslist[position].moviedesc)
            val output = ByteArrayOutputStream()
            movieslist[position].image.compress(Bitmap.CompressFormat.PNG,100,output)
            bundle.putByteArray("image",output.toByteArray())
            fragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction().replace(R.id.source,fragment)
                .addToBackStack("movieslist")
                .commit()
            val trash:MoviesListFragment = MoviesListFragment()
        }
    }
    fun setData(movie: List<Movies>){
        movieslist = movie
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return movieslist.size
    }
}
