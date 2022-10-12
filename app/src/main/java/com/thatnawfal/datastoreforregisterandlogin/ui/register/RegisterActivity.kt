package com.thatnawfal.datastoreforregisterandlogin.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.thatnawfal.datastoreforregisterandlogin.R
import com.thatnawfal.datastoreforregisterandlogin.data.local.datastore.AccountDataStoreManager
import com.thatnawfal.datastoreforregisterandlogin.databinding.ActivityRegisterBinding
import com.thatnawfal.datastoreforregisterandlogin.ui.login.LoginActivity
import com.thatnawfal.datastoreforregisterandlogin.utils.viewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var pref : AccountDataStoreManager
    private val viewModel : RegisterViewModel by viewModelFactory {
        RegisterViewModel(pref)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = AccountDataStoreManager(this)

        binding.btnRegLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btnRegSubmit.setOnClickListener {
            registerAccount()
        }
    }

    private fun registerAccount() {
        if (formValidation()){
            viewModel.registerAccount(
                binding.etRegUsername.text.toString(),
                binding.etRegPass.text.toString()
            )
        }
    }

    private fun formValidation(): Boolean {
        val username = binding.etRegUsername.text
        val password = binding.etRegPass.text
        var valid = false

        if (username.isNullOrEmpty()){
            Toast.makeText(this, "Masukkan Username", Toast.LENGTH_SHORT).show()
        } else { valid = true }
        if (password.isNullOrEmpty()){
            Toast.makeText(this, "Masukkan Password", Toast.LENGTH_SHORT).show()
        } else { valid = true }
        if (username.equals(password)) {
            Toast.makeText(this, "Masukkan Password", Toast.LENGTH_SHORT).show()
        } else { valid = true }

        return valid
    }

}