package com.bbrustol.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbrustol.core.data.infrastructure.ApiError
import com.bbrustol.core.data.infrastructure.ApiException
import com.bbrustol.core.data.infrastructure.ApiSuccess
import com.bbrustol.core.data.repository.JSONPlaceholderRepository
import com.bbrustol.features.home.HomeViewModel.UiState.Catch
import com.bbrustol.features.home.HomeViewModel.UiState.Failure
import com.bbrustol.features.home.HomeViewModel.UiState.Idle
import com.bbrustol.features.home.HomeViewModel.UiState.Success
import com.bbrustol.features.home.model.UsersModel
import com.bbrustol.features.home.model.mappers.toListUsersModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: JSONPlaceholderRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(Idle)
    var uiState: StateFlow<UiState> = _uiState


    init { fetchHeadline() }

    fun fetchHeadline() = viewModelScope.launch {
        repository.getUsers()
            .catch { _uiState.value = Catch(it.message) }
            .collect {
                _uiState.value = when (it) {
                    is ApiError -> Failure(it.code, it.message)
                    is ApiException -> Catch(it.e.message)
                    is ApiSuccess -> Success(it.data.toListUsersModel())
                }
            }
    }

    sealed class UiState {
        data object Idle : UiState()
        data object Loading : UiState()
        data class Success(val usersModel: List<UsersModel>,) : UiState()
        data class Failure(val code: Int, val message: String?) : UiState()
        data class Catch(val message: String?) : UiState()
    }
}