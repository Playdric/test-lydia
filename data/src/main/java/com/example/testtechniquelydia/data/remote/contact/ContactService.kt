package com.example.testtechniquelydia.data.remote.contact

import retrofit2.http.GET
import retrofit2.http.Query

interface ContactService {

    @GET("/api/1.3/")
    suspend fun getContact(@Query("results") results: Int = 20, @Query("page") page: Long = 1, @Query("seed") seed: String = "Lydia"): ContactResponse

}