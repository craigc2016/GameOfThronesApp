package com.xdesign.takehome.interactor

import com.xdesign.takehome.models.ApiCharacter

interface CharacterInteractor {
    suspend fun getCharacters(token: String) : List<ApiCharacter>
}