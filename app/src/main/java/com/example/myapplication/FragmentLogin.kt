package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.ViewModel


class FragmentLogin : Fragment() {
    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val loginbutton = view.findViewById<Button>(R.id.loginbutton)
        val signupbutton = view.findViewById<Button>(R.id.tosignup)
        val email = view.findViewById<EditText>(R.id.loginemailtext)
        val password = view.findViewById<EditText>(R.id.loginpasswordtext)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        signupbutton.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentSignup)
        }

        loginbutton.setOnClickListener {
            validate(email, password)
        }


        return view
    }
    fun validate(email: EditText , password: EditText){
        val nullability = checkNull(email.text.toString(), password.text.toString())
        if(nullability){
            Toast.makeText(context, "please fill out all the data", Toast.LENGTH_LONG).show()
        }
        else{
            val check_exist = exists(email.text.toString())
            if(!check_exist){
                Toast.makeText(context,"email is not exist Signup please",Toast.LENGTH_LONG).show()
            }
            else {
                val passwordconfirmation: String = getPassword(email.text.toString())
                if(passwordconfirmation == password.text.toString()){
                    findNavController().navigate(R.id.action_fragmentLogin_to_moviesListFragment)
                }
                else {
                    Toast.makeText(context,"wrong password",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun checkNull(email:String , password:String): Boolean{
        return (email.isEmpty() || password.isEmpty())
    }

    fun exists(email: String) : Boolean{
        val username:String? = viewModel.existence(email)
        if(username != null){
            return true
        }
        else return false
    }

    fun getPassword(email: String): String{
        return viewModel.getpassword(email)
    }

}