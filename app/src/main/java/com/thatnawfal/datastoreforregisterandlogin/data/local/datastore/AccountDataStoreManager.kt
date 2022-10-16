package com.thatnawfal.datastoreforregisterandlogin.data.local.datastore

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import com.thatnawfal.datastoreforregisterandlogin.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountDataStoreManager(private val ctx: Context) {

    suspend fun registerAccount(usernames: String, passwords: String) {
        ctx.accountDataStore.edit {
            it[username] = usernames
            it[password] = passwords
        }
    }

    suspend fun loginVerify(status : Boolean) {
        ctx.accountDataStore.edit {
            it[isLogin] = status
        }
    }

    suspend fun setImage(stringUri: String) {
        ctx.accountDataStore.edit {
            it[uriImage] = stringUri
        }
    }

    fun checkLogin(): Flow<Boolean>{
        return ctx.accountDataStore.data.map {
            it[isLogin] ?: false
        }
    }

    fun getUsername(): Flow<String> {
        return ctx.accountDataStore.data.map {
            it[username] ?: ""
        }
    }

    fun getPassword(): Flow<String> {
        return ctx.accountDataStore.data.map {
            it[password] ?: ""
        }
    }

    fun getImage(): Flow<String> {
        return ctx.accountDataStore.data.map {
            it[uriImage] ?: ""
        }
    }

    companion object {
        private const val DATASTORE_NAME = "account_preference"

        private val username = stringPreferencesKey("username_key")
        private val password = stringPreferencesKey("password_key")
        private val isLogin = booleanPreferencesKey("isLogin_key")

        private val uriImage = stringPreferencesKey("image_key")
        private val Context.accountDataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}