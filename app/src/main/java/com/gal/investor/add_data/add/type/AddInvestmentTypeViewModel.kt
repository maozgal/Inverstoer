package com.gal.investor.add_data.add.type

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gal.investor.repo.InvestRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddInvestmentTypeViewModel @Inject constructor(
    val repo: InvestRepo
) : ViewModel() {
    private val _showMessage = MutableLiveData<String>()
    private val _clearScreen = MutableLiveData<Any>()

    val showMessage: LiveData<String> by lazy {
        _showMessage
    }
    val clearScreen: LiveData<Any> by lazy {
        _clearScreen
    }

    var isIncome: Boolean = false


    fun press(investmentTypeName: String) {
        if (investmentTypeName.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repo.addNewInvestmentItem(isIncome, investmentTypeName)
                _showMessage.postValue("$investmentTypeName Added Successfully")
                _clearScreen.postValue(Any())
            }
        }
    }
}