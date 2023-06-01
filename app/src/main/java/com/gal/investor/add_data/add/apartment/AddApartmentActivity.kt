package com.gal.investor.add_data.add.apartment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.gal.investor.R
import com.gal.investor.add_data.add.type.AddInvestmentTypeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddApartmentActivity : AppCompatActivity() {
    private val viewModel by viewModels<AddApartmentViewModel>()
    private lateinit var apartmentNameEditText: EditText
    private lateinit var apartmentLocationEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_apartment)

        setUI()
        setObservers()
    }

    private fun setObservers() {
        viewModel.clearScreen.observe(this) {
            apartmentNameEditText.text.clear()
            apartmentLocationEditText.text.clear()
        }
    }

    private fun setUI() {
        apartmentNameEditText = findViewById(R.id.editTextName)
        apartmentLocationEditText = findViewById(R.id.editTextLocation)
        findViewById<Button>(R.id.saveButton).setOnClickListener {
            viewModel.onSaveButtonClicked(
                AddApartmentScreenData(
                    apartmentNameEditText.text
                        .toString(), apartmentLocationEditText.text.toString()
                )
            )
        }
    }
}