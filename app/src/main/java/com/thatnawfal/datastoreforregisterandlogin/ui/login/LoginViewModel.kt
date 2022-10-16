package com.thatnawfal.datastoreforregisterandlogin.ui.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.thatnawfal.datastoreforregisterandlogin.data.local.datastore.AccountDataStoreManager
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: AccountDataStoreManager): ViewModel() {

    fun getUsername() : LiveData<String> {
        return pref.getUsername().asLiveData()
    }
//
    fun getPassword() : LiveData<String> {
        return pref.getPassword().asLiveData()
    }

    fun getLoginStatus(): LiveData<Boolean>{
        return pref.checkLogin().asLiveData()
    }

}