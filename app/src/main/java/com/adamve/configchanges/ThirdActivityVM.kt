package com.adamve.configchanges

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThirdActivityVM : ViewModel() {
    private var _message = MutableLiveData<Int>()
    val message: LiveData<Int> = _message

    fun setMessageId(newValue: Int) {
        _message.postValue(newValue)
    }
}