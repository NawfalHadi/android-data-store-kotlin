package com.thatnawfal.datastoreforregisterandlogin.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.thatnawfal.datastoreforregisterandlogin.data.local.datastore.AccountDataStoreManager
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: AccountDataStoreManager): ViewModel() {

    fun verifyLogin(username: String, password: String) {
        viewModelScope.launch {
            val uname = pref.getUsername().asLiveData().toString()
            val pswor = pref.getPassword().asLiveData().toString()

            if (true){
                pref.loginVerify(true)
            } else {
                pref.loginVerify(false)
            }
        }
    }

    fun getLoginStatus(): LiveData<Boolean>{
        return pref.checkLogin().asLiveData()
    }

}