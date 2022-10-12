package com.thatnawfal.datastoreforregisterandlogin.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.thatnawfal.datastoreforregisterandlogin.R
import com.thatnawfal.datastoreforregisterandlogin.data.local.datastore.AccountDataStoreManager
import com.thatnawfal.datastoreforregisterandlogin.databinding.ActivityLoginBinding
import com.thatnawfal.datastoreforregisterandlogin.ui.MainActivity
import com.thatnawfal.datastoreforregisterandlogin.ui.register.RegisterActivity
import com.thatnawfal.datastoreforregisterandlogin.ui.register.RegisterViewModel
import com.thatnawfal.datastoreforregisterandlogin.utils.viewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var pref : AccountDataStoreManager
    private val viewModel : LoginViewModel by viewModelFactory {
        LoginViewModel(pref)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = AccountDataStoreManager(this)
        observeAction()

        binding.btnLogReg.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogSubmit.setOnClickListener {
            loginAccount()
        }
    }

    private fun loginAccount() {
        if (formValid()){
            viewModel.verifyLogin(
                binding.etLogUsername.text.toString(),
                binding.etLogPass.text.toString()
            )
            viewModel.getLoginStatus().observe(this){
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun formValid(): Boolean {
        val username = binding.etLogUsername.text
        val password = binding.etLogPass.text
        var valid = false

        if (username.isNullOrEmpty()){
            Toast.makeText(this, "Masukkan Username", Toast.LENGTH_SHORT).show()
        } else { valid = true }

        if (password.isNullOrEmpty()){
            Toast.makeText(this, "Masukkan Password", Toast.LENGTH_SHORT).show()
        } else { valid = true }

        return valid
    }

    private fun observeAction() {
        viewModel.getLoginStatus().observe(this){
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}