package com.gal.investor.add_data.add.element

import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.gal.investor.data.entities.Apartment
import com.gal.investor.repo.ContextApartmentRepo
import com.gal.investor.repo.InvestRepo
import com.gal.investor.utils.Enums
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddInvestmentElementViewModel @Inject constructor(
    val repo: InvestRepo,
    private val contextApartment: ContextApartmentRepo
) : ViewModel() {
    private val _showMessage = MutableLiveData<String>()
    private val _clearScreen = MutableLiveData<Any>()
    private val _investmentKindList = MutableLiveData<Array<String>>()
    private val _investmentElementsList = MutableLiveData<Array<String>>()
    private val _selectedApartment = MutableLiveData<Apartment>()

    // Streams
    private val addInvestmentElementScreenData = MutableLiveData<AddInvestmentElementScreenData>()
    private val _saveButtonClicked = MutableLiveData<Any>()

    private lateinit var addInvestmentElementScreenDataObserver: Observer<AddInvestmentElementScreenData>
    private lateinit var saveButtonClickedObserver: Observer<Any>

    val showMessage: LiveData<String> by lazy {
        _showMessage
    }
    val clearScreen: LiveData<Any> by lazy {
        _clearScreen
    }
    val investmentKindList: LiveData<Array<String>> by lazy {
        _investmentKindList
    }
    val investmentElementsList: LiveData<Array<String>> by lazy {
        _investmentElementsList
    }
    val selectedApartment: LiveData<Apartment> by lazy {
        _selectedApartment
    }

    init {
        _investmentKindList.postValue(
            arrayOf(
                Enums.InvestmentKind.INCONE.toString(), Enums
                    .InvestmentKind.EXPENSE.toString()
            )
        )
        _selectedApartment.postValue(contextApartment.currentApartment)
        setObservers()
    }

    private fun setObservers() {
        addInvestmentElementScreenDataObserver = Observer {
            viewModelScope.launch(Dispatchers.IO) {
                it.getIsIncome()?.let { isIncome ->
                    val investmentElements = repo.getInvestmentItemType(isIncome)
                    _investmentElementsList.postValue(investmentElements.map { it.name ?: "" }
                        .toTypedArray())
                }
            }
        }
        saveButtonClickedObserver = Observer {
            addInvestmentElementScreenData.value?.let { screenState ->
                val isIncome = screenState.getIsIncome()
                val investmentType = screenState.investmentType
                if (isIncome == null || investmentType == null) return@Observer
                viewModelScope.launch(Dispatchers.IO) {
                    repo.addInvestmentElement(
                        isIncome = isIncome,
                        investmentTypeName = investmentType,
                        amount = screenState.amount,
                        data = screenState.date,
                        apartmentUid = contextApartment.currentApartment?.uid
                    )
                }
            }
        }

        addInvestmentElementScreenData.observeForever(addInvestmentElementScreenDataObserver)
        _saveButtonClicked.observeForever(saveButtonClickedObserver)
    }

    fun setIsIncomeSelected(screenData: AddInvestmentElementScreenData) {
        addInvestmentElementScreenData.postValue(screenData)
    }

    fun saveButtonClicked(screenState: AddInvestmentElementScreenData) {
        addInvestmentElementScreenData.postValue(screenState)
        _saveButtonClicked.postValue(Any())
    }

    override fun onCleared() {
        super.onCleared()
        addInvestmentElementScreenData.removeObserver(addInvestmentElementScreenDataObserver)
        _saveButtonClicked.removeObserver(saveButtonClickedObserver)
    }


//    fun press(investmentTypeName: String) {
//        if (investmentTypeName.isNotEmpty()) {
//            viewModelScope.launch(Dispatchers.IO) {
//                repo.addNewInvestmentItem(isIncome, investmentTypeName)
//                _showMessage.postValue("$investmentTypeName Added Successfully")
//                _clearScreen.postValue(Any())
//            }
//        }
//    }
}