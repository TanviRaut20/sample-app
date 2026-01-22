package com.tanviraut.sampleapp.data.api

import com.tanviraut.sampleapp.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}