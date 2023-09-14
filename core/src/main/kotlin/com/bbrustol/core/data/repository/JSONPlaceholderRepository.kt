package com.bbrustol.core.data.repository

import com.bbrustol.core.data.di.IoDispatcher
import com.bbrustol.core.data.infrastructure.ApiResult
import com.bbrustol.core.data.infrastructure.handleApi
import com.bbrustol.core.data.remote.newsapi.JSONPlaceholderDataSource
import com.bbrustol.core.data.remote.newsapi.model.response.users.UsersResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JSONPlaceholderRepository @Inject constructor(
    private val remoteJSONPlaceholderDataSource: JSONPlaceholderDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    fun getUsers(): Flow<ApiResult<UsersResponse>> {
        return flow {
            emit(handleApi { remoteJSONPlaceholderDataSource.getUsers() })
        }.flowOn(ioDispatcher)
    }

}