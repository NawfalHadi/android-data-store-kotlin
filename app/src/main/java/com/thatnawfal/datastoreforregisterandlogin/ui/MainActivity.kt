package com.thatnawfal.datastoreforregisterandlogin.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thatnawfal.datastoreforregisterandlogin.data.local.datastore.AccountDataStoreManager
import com.thatnawfal.datastoreforregisterandlogin.databinding.ActivityMainBinding
import com.thatnawfal.datastoreforregisterandlogin.utils.viewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var pref = AccountDataStoreManager(this)
    private val viewModel : MainViewModel by viewModelFactory {
        MainViewModel(pref)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getUsername().observe(this@MainActivity){
            binding.tvHello.text = "Hello, $it"
        }

        binding.logout.setOnClickListener {
            viewModel.logout()
        }
    }
}