package com.bbrustol.core.data.remote.newsapi

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JSONPlaceholderDataSource @Inject constructor(private val JSONPlaceholderApiService: JSONPlaceholderApiService) {

    suspend fun getUsers() = JSONPlaceholderApiService.getUsers()
}