package com.gal.investor.add_data.add.element

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gal.investor.R
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Time
import java.util.*

@AndroidEntryPoint
class AddInvestmentElementActivity : AppCompatActivity() {
    private val viewModel by viewModels<AddInvestmentElementViewModel>()

    private lateinit var kindSpinner: Spinner
    private lateinit var typeSpinner: Spinner
    private lateinit var amountEditText: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var saveButton: Button
    private lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_investment_element)

        setUI()
        setObservation()
    }

    private fun setUI() {
        kindSpinner = findViewById(R.id.is_income_spinner)
        kindSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.setIsIncomeSelected(getScreenState())
            }
        }

        typeSpinner = findViewById(R.id.investment_type_spinner)
        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }
        }

        saveButton = findViewById(R.id.save_button)
        saveButton.setOnClickListener {
            viewModel.saveButtonClicked(getScreenState())
            clearScreen() //TODO callback when everything is fine.
        }

        amountEditText = findViewById(R.id.amount_edit_text)
        datePicker = findViewById(R.id.date_picker)
        title = findViewById(R.id.title_text_view)
    }

    private fun clearScreen() {
        kindSpinner.setSelection(0)
        typeSpinner.setSelection(0)
        amountEditText.text.clear()
        val now = Calendar.getInstance()
        datePicker.updateDate(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
    }

    private fun getScreenState(): AddInvestmentElementScreenData {
        val selectedDate = Calendar.getInstance()
        selectedDate.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
        val amount = amountEditText.text.toString().ifEmpty { "0" }
        return AddInvestmentElementScreenData(
            kindSpinner.selectedItem as? String,
            typeSpinner.selectedItem as? String,
            amount.toDouble(),
            selectedDate.time
        )
    }

    private fun setObservation() {
        viewModel.investmentKindList.observe(this) {
            val adapter = ArrayAdapter(this, R.layout.spinner_item, it)
            kindSpinner.adapter = adapter
        }
        viewModel.investmentElementsList.observe(this) {
            val adapter = ArrayAdapter(this, R.layout.spinner_item, it)
            typeSpinner.adapter = adapter
        }
        viewModel.selectedApartment.observe(this) {
            title.text = "Add Investment Element to "+it.name
        }
    }
}