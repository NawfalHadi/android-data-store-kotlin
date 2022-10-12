package com.thatnawfal.datastoreforregisterandlogin.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thatnawfal.datastoreforregisterandlogin.R
import com.thatnawfal.datastoreforregisterandlogin.databinding.ActivityLoginBinding
import com.thatnawfal.datastoreforregisterandlogin.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogReg.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}