package com.maasbodev.rickandmorty.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maasbodev.rickandmorty.di.IO
import com.maasbodev.rickandmorty.domain.model.Character
import com.maasbodev.rickandmorty.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    @IO private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    var viewState by mutableStateOf<ViewState>(ViewState.Loading)

    fun findById(id: Int) {
        viewModelScope.launch(dispatcher) {
            characterRepository.findById(id)
                .fold({
                    viewState = ViewState.Error(it)
                }, { character ->
                    viewState = ViewState.Success(character)
                })
        }
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Success(val character: Character) : ViewState()
        data class Error(val throwable: Throwable) : ViewState()
    }
}
