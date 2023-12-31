package com.example.bykuliner.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bykuliner.ui.screen.detail.DetailKulinerViewModel
import com.example.bykuliner.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: KulinerRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(DetailKulinerViewModel::class.java)) {
            return DetailKulinerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}