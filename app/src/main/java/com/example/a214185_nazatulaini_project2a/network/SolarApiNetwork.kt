package com.example.a214185_nazatulaini_project2a.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object SolarApiNetwork {
    // Baseline weather API structural endpoint link
    private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

    // Configures compiler parser parameters to safely ignore unmapped JSON fields
    private val jsonConfiguration = Json { ignoreUnknownKeys = true }

    val retrofitService: SolarApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonConfiguration.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(SolarApiService::class.java)
    }
}