package com.example.bykuliner.di

import com.example.bykuliner.data.KulinerRepository

object injection {
    fun providerRepository(): KulinerRepository{
        return KulinerRepository.getInstance()
    }
}