package com.numaan.assesmentrngdev.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginStatus = MutableLiveData<Boolean?>()
    val loginStatus: LiveData<Boolean?> get() = _loginStatus

    fun login(username: String, password: String) {
        if (username == "user@gmail.com" && password == "password") {
            _loginStatus.postValue(true)
        } else {
            _loginStatus.postValue(false)
        }

    }

    fun resetFlag() {
        _loginStatus.value = null
    }
}