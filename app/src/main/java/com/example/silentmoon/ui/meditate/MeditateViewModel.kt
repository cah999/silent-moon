package com.example.silentmoon.ui.meditate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MeditateViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is meditate Fragment"
    }
    val text: LiveData<String> = _text
}