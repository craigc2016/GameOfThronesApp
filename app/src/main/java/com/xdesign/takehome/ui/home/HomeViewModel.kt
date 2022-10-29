package com.xdesign.takehome.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xdesign.takehome.common.Constants
import com.xdesign.takehome.common.Resource
import com.xdesign.takehome.interactor.CharacterInteractor
import com.xdesign.takehome.models.ApiCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterInteractor : CharacterInteractor
) : ViewModel() {

    private val _charactersLiveData = MutableLiveData<Resource<List<ApiCharacter>>>()
    val charactersLiveData: LiveData<Resource<List<ApiCharacter>>> get() = _charactersLiveData

    fun getCharacters() {
        _charactersLiveData.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val response = characterInteractor.getCharacters(Constants.TOKEN)
                _charactersLiveData.value = Resource.Success(response)
            } catch (e: Exception) {
                _charactersLiveData.value = Resource.Error(e.message.toString())
            }
        }
    }

    fun searchCharacter(name : String) {
        try {
            val result = _charactersLiveData.value?.data?.filter { it.name.contains(name, ignoreCase = true) }
            if (result?.isNotEmpty() == true) {
                _charactersLiveData.value = Resource.Success(result)
            } else {
                _charactersLiveData.value = Resource.Error("No Character found please search again")
            }
        }catch (e: Exception) {
            _charactersLiveData.value = Resource.Error(e.message.toString())
        }
    }
}