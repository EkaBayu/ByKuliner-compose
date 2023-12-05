package com.example.bykuliner.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bykuliner.data.KulinerRepository
import com.example.bykuliner.model.GetKuliner
import com.example.bykuliner.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailKulinerViewModel(private val repository: KulinerRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<GetKuliner>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<GetKuliner>>
        get() = _uiState


    fun getKulinerById(kulinerId: Int){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getKulinerById(kulinerId))
        }

    }
}