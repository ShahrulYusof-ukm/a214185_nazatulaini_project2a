package com.example.a214185_nazatulaini_project2a.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SolarForecastResponse(
    @SerialName("peak_radiance") val peakRadiance: Double,
    @SerialName("estimated_output_kw") val estimatedOutputKw: Double,
    @SerialName("cloud_coverage") val cloudCoverage: Int,
    @SerialName("condition") val condition: String
)