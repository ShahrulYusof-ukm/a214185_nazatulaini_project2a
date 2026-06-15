package com.example.a214185_nazatulaini_project2a.network

import retrofit2.http.GET
import retrofit2.http.Query

interface SolarApiService {
    // Defines an HTTP GET request to retrieve target clean energy metrics
    @GET("api/solar-radiance")
    suspend fun getCleanEnergyForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): SolarForecastResponse
}