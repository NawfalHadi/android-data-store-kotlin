package com.thatnawfal.datastoreforregisterandlogin.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.thatnawfal.datastoreforregisterandlogin.data.local.datastore.AccountDataStoreManager
import com.thatnawfal.datastoreforregisterandlogin.databinding.ActivityLoginBinding
import com.thatnawfal.datastoreforregisterandlogin.ui.MainActivity
import com.thatnawfal.datastoreforregisterandlogin.ui.register.RegisterActivity
import com.thatnawfal.datastoreforregisterandlogin.utils.viewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private var pref = AccountDataStoreManager(this)
    private val viewModel : LoginViewModel by viewModelFactory {
        LoginViewModel(pref)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            if (verifyLogin(
                    binding.etLogUsername.text.toString(),
                    binding.etLogPass.text.toString()
            )) {
                startActivity(Intent(this@LoginActivity , MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "false", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun verifyLogin(username: String, password: String): Boolean {
        var uname = ""
        var pass = ""
        viewModel.getUsername().observe(this) {
            uname = it.toString()
        }

        viewModel.getPassword().observe(this) {
            pass = it.toString()
        }
        return (uname == username) && (pass == password)
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
            if (it) startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
    }
}