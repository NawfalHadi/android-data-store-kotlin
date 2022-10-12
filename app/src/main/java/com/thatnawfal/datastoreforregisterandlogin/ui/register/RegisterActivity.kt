package com.thatnawfal.datastoreforregisterandlogin.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thatnawfal.datastoreforregisterandlogin.R
import com.thatnawfal.datastoreforregisterandlogin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}