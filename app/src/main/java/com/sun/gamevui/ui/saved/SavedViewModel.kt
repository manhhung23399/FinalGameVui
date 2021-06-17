package com.sun.gamevui.ui.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.gamevui.base.BaseViewModel
import com.sun.gamevui.data.model.Game
import com.sun.gamevui.data.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedViewModel(
    private val repo: GameRepository
) : BaseViewModel() {

    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>>
        get() = _games

    init {
        getSavedGames()
    }

    private fun getSavedGames() {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val savedGames = repo.getSavedGames()
            _games.postValue(savedGames)
            _isLoading.postValue(false)
        }
    }

    fun deleteGame(game: Game) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            repo.deleteGame(game)
            getSavedGames()
        }
    }
}
