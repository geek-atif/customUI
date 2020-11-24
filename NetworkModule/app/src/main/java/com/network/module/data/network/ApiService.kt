package com.network.module.data.network

import com.network.module.model.UIModel
import retrofit2.http.GET


/**
 * Created by Atif Qamar
 */
interface ApiService {
    @GET("mobileapps/android_assignment.json")
    suspend fun fetchCustomUI(): UIModel
}