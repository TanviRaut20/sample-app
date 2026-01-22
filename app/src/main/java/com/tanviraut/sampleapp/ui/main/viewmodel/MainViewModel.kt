package com.tanviraut.sampleapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanviraut.sampleapp.data.repository.MainRepository

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

}
