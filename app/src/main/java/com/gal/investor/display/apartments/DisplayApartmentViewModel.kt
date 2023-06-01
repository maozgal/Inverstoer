package com.gal.investor.display.apartments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gal.investor.data.entities.Apartment
import com.gal.investor.repo.ContextApartmentRepo
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
class DisplayApartmentViewModel @Inject constructor(
    private val repo: InvestRepo,
    private val contextApartment: ContextApartmentRepo
) : ViewModel() {
    private val _showMessage = MutableLiveData<String>()
    private val _apartmentsList = MutableLiveData<List<Apartment>>()

    val showMessage: LiveData<String> by lazy {
        _showMessage
    }
    val apartmentsList: LiveData<List<Apartment>> by lazy {
        _apartmentsList
    }

    init {
        viewModelScope.launch(Dispatchers.IO){
            _apartmentsList.postValue(repo.getAllApartments())
        }
    }

    fun apartmentSelected(apartment: Apartment) {
        contextApartment.currentApartment = apartment
        _showMessage.postValue("${apartment.name}: selected!")
    }
}