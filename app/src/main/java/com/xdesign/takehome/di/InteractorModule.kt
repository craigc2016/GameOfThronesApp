package com.xdesign.takehome.di

import com.xdesign.takehome.api.CharacterService
import com.xdesign.takehome.interactor.CharacterInteractor
import com.xdesign.takehome.interactor.CharacterInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InteractorModule {

    @Singleton
    @Provides
    fun providesCharacterInteractorImpl(api: CharacterService) : CharacterInteractor {
        return CharacterInteractorImpl(api)
    }
}