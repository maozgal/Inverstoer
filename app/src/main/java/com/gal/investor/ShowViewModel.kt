package com.gal.investor

import android.content.Context
import android.widget.EditText
import android.widget.Toast
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
class ShowViewModel @Inject constructor(
    val repo: InvestRepo
) : ViewModel() {
    private val _data = MutableLiveData<String>()

    val data: LiveData<String> by lazy {
        _data
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val sb = StringBuilder()
            sb.append("Types:\n")
            sb.append(repo.getAll().toString())
            sb.append("\n\nElements:\n")
            sb.append("Income:\n")
            sb.append(repo.getAllInvestmentElements(true).toString())
            sb.append("\nExpense:\n")
            sb.append(repo.getAllInvestmentElements(false).toString())
            sb.append("\nApartments:\n")
            sb.append(repo.getAllApartments().toString())

            _data.postValue(sb.toString())
        }
    }

}