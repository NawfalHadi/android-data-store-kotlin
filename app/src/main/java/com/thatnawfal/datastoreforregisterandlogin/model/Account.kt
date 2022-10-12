package com.thatnawfal.datastoreforregisterandlogin.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

data class Account(
    val username: String,
    val password: String,
    val isLogin: Boolean
) : Flow<Account> {
    override suspend fun collect(collector: FlowCollector<Account>) {
        TODO("Not yet implemented")
    }
}
