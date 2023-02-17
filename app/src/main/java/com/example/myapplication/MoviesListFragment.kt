package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.MovieModel
import com.example.myapplication.data.Movies
import com.example.myapplication.databinding.FragmentMoviesListBinding
import java.lang.Exception

class MoviesListFragment : Fragment() {
    val PICKIMAGE = 20
    lateinit var viewmodel:MovieModel
    lateinit var binding:FragmentMoviesListBinding
    lateinit var imagepath:Uri
    lateinit var imagetostore:Bitmap
    lateinit var movie: Movies
    lateinit var titletv:String
    lateinit var desctv:String
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProvider(this).get(MovieModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movies_list,container,false)
        val adapter: movieadapter = movieadapter()
        val movieview = binding.recycler
        movieview.adapter = adapter
        movieview.layoutManager = LinearLayoutManager(context)
        val movies:List<Movies> = viewmodel.getmovie
        adapter.setData(movies)
        binding.addnewbutton.setOnClickListener {
            titletv = binding.enteredtitletv.text.toString()
            desctv = binding.entereddescriptiontv.text.toString()
            if(!(titletv.isEmpty() || desctv.isEmpty())) {
                chooseImage()
            }
            else
                Toast.makeText(context,"enter name and description",Toast.LENGTH_LONG).show()
        }


        return binding.root

    }

    public fun chooseImage(){
        try{
            val intent: Intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(intent,PICKIMAGE)
        }catch (e:Exception){
            Toast.makeText(context,"image error",Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == PICKIMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
                imagepath = data.data!!
                imagetostore = MediaStore.Images.Media.getBitmap(context?.contentResolver,imagepath)
                movie = Movies(titletv.toString(), desctv.toString(), imagetostore)
                viewmodel.addmovie(movie)
            }
        }catch (e:Exception){
            Toast.makeText(context,"result error",Toast.LENGTH_LONG).show()
        }
    }


}