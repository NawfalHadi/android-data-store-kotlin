package com.thatnawfal.datastoreforregisterandlogin.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thatnawfal.datastoreforregisterandlogin.R
import com.thatnawfal.datastoreforregisterandlogin.data.local.datastore.AccountDataStoreManager
import com.thatnawfal.datastoreforregisterandlogin.databinding.ActivityMainBinding
import com.thatnawfal.datastoreforregisterandlogin.ui.register.RegisterViewModel
import com.thatnawfal.datastoreforregisterandlogin.utils.viewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var pref : AccountDataStoreManager
    private val viewModel : MainViewModel by viewModelFactory {
        MainViewModel(pref)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getUserData().observe(this@MainActivity){
            binding.tvHello.text = "Hello, $it "
        }
    }
}