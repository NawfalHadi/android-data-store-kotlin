package com.thatnawfal.datastoreforregisterandlogin.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thatnawfal.datastoreforregisterandlogin.data.local.datastore.AccountDataStoreManager
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: AccountDataStoreManager): ViewModel() {

    fun verifyLogin(username: String, password: String) {
        viewModelScope.launch {
            pref.loginVerify(username, password)
        }
    }

}