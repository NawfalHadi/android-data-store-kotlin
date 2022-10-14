package com.thatnawfal.datastoreforregisterandlogin.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.thatnawfal.datastoreforregisterandlogin.data.local.datastore.AccountDataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val pref: AccountDataStoreManager): ViewModel() {

    fun registerAccount(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            pref.registerAccount(username, password)
        }
    }

    fun getUsername() : LiveData<String> {
        return pref.getUsername().asLiveData()
    }



}