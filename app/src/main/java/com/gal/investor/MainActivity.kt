package com.gal.investor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.gal.investor.utils.Consts.IS_INCOME
import com.gal.investor.add_data.add.type.AddInvestmentTypeActivity
import com.gal.investor.data.InvestmentDB


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.expense_button).setOnClickListener {
            val intent = Intent(this, AddInvestmentTypeActivity::class.java)
            intent.putExtra(IS_INCOME, false)
            startActivity(intent)
        }
        findViewById<Button>(R.id.income_button).setOnClickListener {
            val intent = Intent(this, AddInvestmentTypeActivity::class.java)
            intent.putExtra(IS_INCOME, true)
            startActivity(intent)
        }

        val db = Room.databaseBuilder(
            applicationContext,
            InvestmentDB::class.java, "investment-db"
        ).build()

        val investmentItemDAO = db.investmentItemDAO()

//        CoroutineScope(Dispatchers.IO).launch {
//            val investmentItemType = InvestmentItemType(isIncome = true, name = "ggg")
//            investmentItemDAO.insertAll(investmentItemType)
//            val investmentItems: List<InvestmentItemType> = investmentItemDAO.getAll()
//            runOnUiThread {
//                findViewById<TextView>(R.id.tv1).text = investmentItems.toString()
//            }
//
//        }

    }
}