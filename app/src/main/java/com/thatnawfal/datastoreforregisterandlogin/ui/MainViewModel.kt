package com.thatnawfal.datastoreforregisterandlogin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.thatnawfal.datastoreforregisterandlogin.data.local.datastore.AccountDataStoreManager
import kotlinx.coroutines.launch

class MainViewModel(private val pref: AccountDataStoreManager): ViewModel() {

    fun getUsername() : LiveData<String>{
        return pref.getUsername().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.loginVerify(false)
        }
    }

    fun setImage(stringUri: String) {
        viewModelScope.launch {
            pref.setImage(stringUri)
        }
    }

    fun getImage(): LiveData<String> {
        return pref.getImage().asLiveData()
    }


}