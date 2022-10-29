package com.xdesign.takehome.interactor

import com.xdesign.takehome.api.CharacterService
import com.xdesign.takehome.models.ApiCharacter

class CharacterInteractorImpl(private val api: CharacterService) : CharacterInteractor {

    override suspend fun getCharacters(token: String): List<ApiCharacter> {
        return api.getCharacters(token)
    }

}