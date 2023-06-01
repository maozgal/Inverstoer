package com.gal.investor.add_data.add.apartment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gal.investor.repo.InvestRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

data class AddApartmentScreenData(
    val name: String,
    val location: String
)

@HiltViewModel
class AddApartmentViewModel @Inject constructor(
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

    fun onSaveButtonClicked(addApartmentScreenData: AddApartmentScreenData) {
        if (addApartmentScreenData.name.isNotEmpty() && addApartmentScreenData.location
                .isNotEmpty()){
            viewModelScope.launch(Dispatchers.IO) {
                repo.addNewApartment(addApartmentScreenData.name, addApartmentScreenData.location)
                _showMessage.postValue("${addApartmentScreenData.name} Added Successfully")
                _clearScreen.postValue(Any())
            }
        }
    }

}