package com.example.bykuliner.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bykuliner.data.KulinerRepository
import com.example.bykuliner.model.GetKuliner
import com.example.bykuliner.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: KulinerRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<GetKuliner>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<GetKuliner>>>
        get() = _uiState

    fun getAllKuliner(){
        viewModelScope.launch {
            repository.getAllKuliner()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{kuliner ->
                    _uiState.value = UiState.Success(kuliner)

                }
        }
    }
}