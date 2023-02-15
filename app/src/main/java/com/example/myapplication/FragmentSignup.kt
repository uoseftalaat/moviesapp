package com.example.myapplication

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.User
import com.example.myapplication.data.ViewModel


class FragmentSignup : Fragment() {

    private lateinit var viewModel:ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        val view =  inflater.inflate(R.layout.fragment_signup, container, false)
        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)
        val button = view.findViewById<Button>(R.id.signup)
        button.setOnClickListener {
            insert_into_database(email,password)
        }
        return view
    }

    fun insert_into_database(email: EditText, password: EditText){
        val nullability = checkNull(email.text.toString(), password.text.toString())
        val lenght = checklenght(password.text.toString())
        if(nullability)
        {
            Toast.makeText(context, "please fill out all the data", Toast.LENGTH_LONG).show()
        }
        else
        {
            if(!lenght){
                Toast.makeText(context,"password must be 8 or more",Toast.LENGTH_LONG).show()
            }
            else
            {
                val user = User(email.text.toString(), password.text.toString())
                viewModel.adduser(user)
                findNavController().navigate(R.id.action_fragmentSignup_to_fragmentLogin)
            }
        }
    }

    fun checkNull(email:String, password:String): Boolean{
        return (TextUtils.isEmpty(email) && TextUtils.isEmpty(password))
    }

    fun checklenght(password:String): Boolean{
        return (password.length >= 8)
    }
}