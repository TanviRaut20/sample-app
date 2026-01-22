package com.tanviraut.sampleapp.data.repository

import com.tanviraut.sampleapp.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper){
    suspend fun getUsers() = apiHelper.getUsers()
}