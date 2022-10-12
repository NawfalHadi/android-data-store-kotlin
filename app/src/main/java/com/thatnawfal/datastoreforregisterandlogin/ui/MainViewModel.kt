package com.thatnawfal.datastoreforregisterandlogin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.thatnawfal.datastoreforregisterandlogin.data.local.datastore.AccountDataStoreManager

class MainViewModel(private val pref: AccountDataStoreManager): ViewModel() {

    fun getUserData(): LiveData<Set<String>> {
        return pref.getAllData().asLiveData()
    }
}