package com.sun.gamevui.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.gamevui.base.BaseViewModel
import com.sun.gamevui.data.model.Game
import com.sun.gamevui.data.model.Genre
import com.sun.gamevui.data.repository.GameRepository
import com.sun.gamevui.data.repository.GenreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val gameRepo: GameRepository,
    private val genreRepo: GenreRepository
) : BaseViewModel() {
    private val _popularGames = MutableLiveData<List<Game>>()
    val popularGames: LiveData<List<Game>>
        get() = _popularGames
    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>>
        get() = _genres
    private val _gamesByGenre = MutableLiveData<List<Game>>()
    val gamesByGenre: LiveData<List<Game>>
        get() = _gamesByGenre
    private val _gamesByGenre2 = MutableLiveData<List<Game>>()
    val gamesByGenre2: LiveData<List<Game>>
        get() = _gamesByGenre2
    private val _gamesByGenre3 = MutableLiveData<List<Game>>()
    val gamesByGenre3: LiveData<List<Game>>
        get() = _gamesByGenre3

    fun getPopularGames(platform: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val gamesFromApi = gameRepo.getPopularGames(platform)
            _popularGames.postValue(gamesFromApi.results)
        }
    }

    fun getListGenres() {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val genresFromApi = genreRepo.getGenres()
            _genres.postValue(genresFromApi.results)
        }
    }

    fun getGamesByGenre(genre: String, platform: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val gamesByGenreFromApi = gameRepo.getGamesByGenre(genre, platform)
            _gamesByGenre.postValue(gamesByGenreFromApi.results)
            _isLoading.postValue(false)
        }
    }

    fun getGamesByGenre2(genre: String, platform: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val gamesByGenreFromApi = gameRepo.getGamesByGenre(genre, platform)
            _gamesByGenre2.postValue(gamesByGenreFromApi.results)
            _isLoading.postValue(false)
        }
    }

    fun getGamesByGenre3(genre: String, platform: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val gamesByGenreFromApi = gameRepo.getGamesByGenre(genre, platform)
            _gamesByGenre3.postValue(gamesByGenreFromApi.results)
            _isLoading.postValue(false)
        }
    }
}
