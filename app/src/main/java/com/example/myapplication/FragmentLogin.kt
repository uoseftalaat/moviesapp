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
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.ViewModel
import com.example.myapplication.databinding.FragmentLoginBinding


class FragmentLogin : Fragment() {
    private lateinit var viewModel: ViewModel
    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        binding.tosignup.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentSignup)
        }

        binding.loginbutton.setOnClickListener {
            validate(binding.loginemailtext, binding.loginpasswordtext)
        }


        return binding.root
    }
    fun validate(email: EditText , password: EditText){
        val nullability = viewModel.checkNull(email.text.toString(), password.text.toString())
        if(nullability){
            Toast.makeText(context, "please fill out all the data", Toast.LENGTH_LONG).show()
        }
        else{
            val check_exist = viewModel.exists(email.text.toString())
            if(!check_exist){
                Toast.makeText(context,"email is not exist Signup please",Toast.LENGTH_LONG).show()
            }
            else {
                val passwordconfirmation: String = viewModel.getPassword(email.text.toString())
                if(passwordconfirmation == password.text.toString()){
                    findNavController().navigate(R.id.action_fragmentLogin_to_moviesListFragment)
                }
                else {
                    Toast.makeText(context,"wrong password",Toast.LENGTH_LONG).show()
                }
            }
        }
    }



}