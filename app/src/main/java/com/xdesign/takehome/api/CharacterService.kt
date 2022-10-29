package com.xdesign.takehome.api

import com.xdesign.takehome.models.ApiCharacter
import retrofit2.http.GET
import retrofit2.http.Header

interface CharacterService {
    @GET("/characters")
    suspend fun getCharacters(@Header("Authorization") token: String): List<ApiCharacter>
}