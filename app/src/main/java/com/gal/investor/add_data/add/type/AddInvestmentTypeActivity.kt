package com.gal.investor.add_data.add.type

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gal.investor.utils.Consts.IS_INCOME
import com.gal.investor.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddInvestmentTypeActivity : AppCompatActivity() {
    private val viewModel by viewModels<AddInvestmentTypeViewModel>()
    private lateinit var investmentTypeNameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_investment_type)

        val isIncome = intent.getBooleanExtra(IS_INCOME, false)
        viewModel.isIncome = isIncome

        if (isIncome){
            setIncomeMode()
        } else {
            setExpenseMode()
        }

        val investmentTypeNameEditText: EditText = findViewById(R.id.editTextInvestmentType)
        findViewById<Button>(R.id.saveButton).setOnClickListener {
            viewModel.press(investmentTypeNameEditText.text.toString())
        }

        viewModel.clearScreen.observe(this) {
            investmentTypeNameEditText.text.clear()
        }
    }

    private fun setIncomeMode() {
        findViewById<TextView>(R.id.title).text = "Add new income type"
        findViewById<EditText>(R.id.editTextInvestmentType).hint = "Add new income type"
        findViewById<View>(R.id.background).setBackgroundColor(Color.parseColor("#d4fad4"))
    }

    private fun setExpenseMode() {
        findViewById<TextView>(R.id.title).text = "Add new expense type"
        findViewById<EditText>(R.id.editTextInvestmentType).hint = "Add new expense type"
        findViewById<View>(R.id.background).setBackgroundColor(Color.parseColor("#ffdbdb"))
    }
}