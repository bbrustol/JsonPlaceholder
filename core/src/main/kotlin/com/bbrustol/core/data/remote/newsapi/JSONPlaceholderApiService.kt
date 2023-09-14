package com.bbrustol.core.data.remote.newsapi

import com.bbrustol.core.data.remote.newsapi.model.response.users.UsersResponse
import retrofit2.Response
import retrofit2.http.GET

fun interface JSONPlaceholderApiService {
    @GET("users")
    suspend fun getUsers(): Response<UsersResponse>

}