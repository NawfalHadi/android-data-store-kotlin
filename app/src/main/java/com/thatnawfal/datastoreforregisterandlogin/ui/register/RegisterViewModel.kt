package com.thatnawfal.datastoreforregisterandlogin.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thatnawfal.datastoreforregisterandlogin.data.local.datastore.AccountDataStoreManager
import kotlinx.coroutines.launch

class RegisterViewModel(private val pref: AccountDataStoreManager): ViewModel() {

    fun registerAccount(username: String, password: String) {
        viewModelScope.launch {
            pref.registerAccount(username, password)
        }
    }




}