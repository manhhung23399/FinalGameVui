package com.sun.gamevui.ui.tag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.gamevui.base.BaseViewModel
import com.sun.gamevui.data.model.Game
import com.sun.gamevui.data.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TagViewModel(
    private val gameRepo: GameRepository
) : BaseViewModel() {
    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>>
        get() = _games

    fun getTagGenre(genreId: Long) {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val gamesFromApi = gameRepo.getTagGenre(genreId)
            _games.postValue(gamesFromApi.results)
            _isLoading.postValue(false)
        }
    }

    fun getTagDeveloper(developerId: Long) {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val gamesFromApi = gameRepo.getTagDeveloper(developerId)
            _games.postValue(gamesFromApi.results)
            _isLoading.postValue(false)
        }
    }

    fun getTagPublisher(publisherId: Long) {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val gamesFromApi = gameRepo.getTagPublisher(publisherId)
            _games.postValue(gamesFromApi.results)
            _isLoading.postValue(false)
        }
    }
}
